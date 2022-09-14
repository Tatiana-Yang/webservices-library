const mongoose = require('mongoose');

const UserRole = mongoose.model(
    "UserRole",
    new mongoose.Schema({
        description: {
            type: String,
            required: true
        }
    }, { collection: 'UserRole' })
);


module.exports = UserRole;
