const express = require('express');
const bcrypt = require('bcryptjs');
const router = new express.Router();

const db = require("../../models");
const User = db.user;


/**
 * @swagger
 * /api/users/getId:
 *   post:
 *     summary: Get the ID of a user according to its login
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
 *                 example: qwerty
 *     responses:
 *       200:
 *         description: The ID has been found
 *       500:
 *         description: User not found
 *       401:
 *         description: Unauthorized access. User not authenticated
 */
router.post("/getId", async function (req, res) {
    if (req.user) {
        let user = await User.findOne({login: req.body.login});
        if(user) {
            return res.status(200).send(String(user._id));
        }
        return res.status(500).send("No user found");
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
 * /api/users/getUser:
 *   post:
 *     summary: Get a user according to its ID
 *     tags: [Users]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               id:
 *                 type: string
 *                 description: ID
 *                 example: 123456789012345678
 *     responses:
 *       200:
 *         description: The user has been found
 *       500:
 *         description: ID not found
 *       401:
 *         description: Unauthorized access. User not authenticated
 */
router.post("/getUser", async function (req, res) {
    if (req.user) {
        let user = await User.findById(req.body.id);
        return res.status(200).send(user);
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
 * /api/users/changeLogin:
 *   post:
 *     summary: Change login of the user
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
 *                 example: qwerty
 *     responses:
 *       200:
 *         description: The login has been changed
 *       500:
 *         description: Login already exists
 *       401:
 *         description: Unauthorized access. User not authenticated
 */
router.post("/changeLogin", async function(req, res) {
    if (req.user) {
        let user = await User.findOne({login: req.body.login});
        if(user) {
            return res.status(500).send('Login already exists');
        }
        req.user.updateOne({$set:{login:req.body.login}}, function(err) {
            if(!err) {
                console.log("Login successfully changed");
                res.send("Changed");
            }
            else {
                console.log(err)
            }
        });
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
 * /api/users/changePassword:
 *   post:
 *     summary: Change password of the user
 *     tags: [Users]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               password:
 *                 type: string
 *                 description: password
 *                 example: T0P-53CR3T
 *     responses:
 *       200:
 *         description: The password has been changed
 *       401:
 *         description: Unauthorized access. User not authenticated
 */
router.post("/changePassword", function(req, res) {
    if (req.user) {
        req.user.updateOne({$set: {password: bcrypt.hashSync(req.body.password, 8)}}, function (err) {
            if (!err) {
                console.log("Password successfully changed");
                res.status(200).send("Changed");
            } else {
                console.log(err);
            }
        });
    }
    else {
        return res.status(401).send({
            message: 'Unauthorized',
            description: 'You are not logged in',
        });
    }
});


module.exports = router;