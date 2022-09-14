const express = require('express');

const usersRouter = require('./users/users');
const userRoleRouter = require('./userRoles');

const router = new express.Router();
router.use('/api/users', usersRouter);
router.use('/api/userRole', userRoleRouter);

module.exports = router;