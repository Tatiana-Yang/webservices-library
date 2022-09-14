const express = require('express');
const passport = require('../../utils/passport');
const bcrypt = require('bcryptjs');
const router = new express.Router();

const db = require("../../models");
const User = db.user;

let logOut = function(req, res) {
    req.logout();
    console.log("Logged out");
    req.session = null;
    res.clearCookie("session");
    res.clearCookie("session.sig");
    res.redirect('/');
}

function randomPassword() {
    let password = '';
    let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    for(let i = 0; i < 8; i++) {
        password += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return password;
}

///////////////////////
// GERER L'ARCHIVAGE //
///////////////////////


/**
 * @swagger
 * /api/users/signUp:
 *   post:
 *     summary: Register a user
 *     tags: [Users]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               firstname:
 *                 type: string
 *                 description: firstname
 *                 example: Alain
 *               lastname:
 *                 type: string
 *                 description: lastname
 *                 example: Proviste
 *               isBorrower:
 *                 type: boolean
 *                 description: is a borrower
 *                 example: false
 *               login:
 *                 type: string
 *                 description: login
 *                 example: aproviste
 *               password:
 *                 type: string
 *                 description: password
 *                 example: 1234
 *     responses:
 *       200:
 *         description: The user has been successfully registered
 *       500:
 *         description: An error occurred
 *       400:
 *         description: Login already in use
 *
 */
router.post("/signUp", async (req, res) => {
    // CONSULT
    if(!req.user.isBorrower) {
        return res.status(503).send({message: 'Accès non autorisé'});
    }
    else {
        // ADMIN can create every user
        // BORROW and CONTRIB can only create CONSULT users
        if(req.user.role !== '614f6aadef41dbbcfc990707') {
            if(req.body.role !== '614f6a47ef41dbbcfc990704') {
                return res.status(503).send({message: 'Accès non autorisé'});
            }
        }
    }


    let user = await User.findOne({
        login: req.body.login
    });
    if(user !== null) {
        res.status(400).send({ message: "Erreur : login déjà existant" });
        return "Failed! Login is already in use!";
    }
    let isBorrower = req.body.role !== '614f6a47ef41dbbcfc990704';
    let password = randomPassword();
    console.log(password);
    const newUser = new User({
        firstname: req.body.firstname,
        lastname: req.body.lastname,
        isBorrower: isBorrower,
        login: req.body.login,
        password: bcrypt.hashSync(password, 8),
        role: req.body.role
    });

    newUser.save((err, newUser) => {
        if (err) {
            res.status(500).send({ message: err });
            return err;
        }
        else {
            console.log("Password is : " + password);
            res.send({ message: "User was successfully registered!" });
            return newUser;
        }
    });
});


/**
 * @swagger
 * /api/users/signIn:
 *   post:
 *     summary: Authenticate a user via passport.js
 *     tags: [Users]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               login:
 *                 type: string
 *                 description: login
 *                 example: alainproviste
 *               password:
 *                 type: string
 *                 description: password
 *                 example: 1234
 *     responses:
 *       200:
 *         description: The user has been successfully authenticated
 *       500:
 *         description: An error occurred
 *       401:
 *         description: Unauthorized access. Incorrect login or password
 *
 */
router.post("/signIn", (req, res, next) => {
    passport.authenticate('signIn', function(err, user, next) {

        if (err) {
            return res.status(500).send(err);
        }

        if (!user) {
            return res.status(401).send({
                message: 'Unauthorized',
                description: 'You are not logged in',
            });
        }

        req.logIn(user, function(err) {
            if (err) {
                return next(err);
            } else {
                console.log('Logged in');
            }
        });

        return res.json(user);
    })(req, res, next);
});


/**
 * @swagger
 * /api/users/isLoggedIn:
 *   get:
 *     summary: Check if a user is authenticated (active session)
 *     tags: [Users]
 *     responses:
 *       200:
 *         description: The user is connected
 *       401:
 *         description: The user isn't connected
 */
router.get("/isLoggedIn", (req, res) => {
    if (req.user) {
        return res.send(req.user);
    }
    else {
        return res.status(401).send({
            message: 'Unauthorized',
            description: 'You are not logged in',
        });
    }
});


/**
 * @swagger
 * /api/users/signOut:
 *   get:
 *     summary: Disconnect a user and kill its session
 *     tags: [Users]
 *     responses:
 *       200:
 *         description: The user has been successfully disconnected
 */
router.get("/signOut", logOut);

module.exports = router;
