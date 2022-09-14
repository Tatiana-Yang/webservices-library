/**
 * @swagger
 *   tags:
 *     name: Users
 *     description: manage the users
 */

const express = require('express');

const sessionRouter = require('./session');
const managerRouter = require('./manager');

const router = new express.Router();
router.use('/', sessionRouter);
router.use('/', managerRouter);

module.exports = router;