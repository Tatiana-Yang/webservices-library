const mongoose = require('mongoose');

const User = mongoose.model(
    "User",
    new mongoose.Schema({
        firstname: {
            type: String,
            required: true
        },
        lastname: {
            type: String,
            required: true
        },
        dateInscription: {
            type: Date,
            default: new Date()
        },
        isBorrower: {
            type: Boolean,
            required: true
        },
        login: {
            type: String,
            required: true
        },
        password: {
            type: String,
            required: true
        },
        role: {
            type: String,
            required: true
        }
    }, { collection: 'User' })
);


module.exports = User;
