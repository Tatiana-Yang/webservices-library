/**
 * @swagger
 *   tags:
 *     name: UserRole
 *     description: manage the role of users
 */
const express = require('express');
const router = new express.Router();

const db = require("../models");
const UserRole = db.userRole;

/**
 * @swagger
 * /api/userRole/getAll:
 *   get:
 *     summary: Get all roles of users
 *     tags: [UserRole]
 *     responses:
 *       200:
 *         description: Roles has been retrieved
 *       401:
 *         description: Unauthorized access. User not authenticated
 */
router.get("/getAll", async (req, res) => {
    if (req.user) {
        let userRoles = await UserRole.find({});
        return res.send(userRoles);
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
 * /api/userRole/getRole:
 *   post:
 *     summary: Get a role according to its ID
 *     tags: [UserRole]
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
 *         description: The role has been found
 *       500:
 *         description: ID not found
 *       401:
 *         description: Unauthorized access. User not authenticated
 */
router.post("/getRole", async function (req, res) {
    if (req.user) {
        let userRole = await UserRole.findById(req.body.id);
        return res.status(200).send(userRole);
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
 * /api/userRole/getId:
 *   post:
 *     summary: Get a role ID according to the role description
 *     tags: [UserRole]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               desc:
 *                 type: string
 *                 description: description
 *                 example: CONSULT
 *     responses:
 *       200:
 *         description: The role ID has been found
 *       500:
 *         description: Role description not found
 *       401:
 *         description: Unauthorized access. User not authenticated
 */
router.post("/getId", async function (req, res) {
    if (req.user) {
        let userRole = await UserRole.findOne({ description: req.body.desc });
        return res.status(200).send(userRole._id);
    }
    else {
        return res.status(401).send({
            message: 'Unauthorized',
            description: 'You are not logged in',
        });
    }
});

module.exports = router;