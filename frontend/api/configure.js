const express = require('express');
const cookieSession = require('cookie-session');
const bodyParser = require('body-parser');
const path = require('path');
const passport = require('./utils/passport');
const apiRouter = require('./routes/api');
const db = require("./models");
//const cors = require('cors');
require('dotenv').config();


db.mongoose
    .connect(process.env.DB_CONNECTION, {
        useNewUrlParser: true,
        useUnifiedTopology: true
    })
    .then(() => {
        console.log("Successfully connect to MongoDB.");
    })
    .catch(err => {
        console.error("Connection error", err);
        process.exit();
    });




module.exports = (app) => {
    // enable all CORS
    //app.use(cors());

    // parse application/json
    app.use(bodyParser.json());

    // parse requests of content-type - application/x-www-form-urlencoded
    app.use(bodyParser.urlencoded({ extended: true }));

    app.use(express.static(path.join(__dirname, '../dist')));


    app.use(cookieSession({
        name: 'session',
        keys: ['vueauthrandomkey'],
        maxAge: 60 * 60 * 1000 // 1 hour
    }));

    app.use(passport.initialize());
    app.use(passport.session());

    app.use(apiRouter);

    // simple route
    app.get("/", (req, res) => {
        res.sendFile(path.join(__dirname, '../dist/index.html'));
    });

    app.get("/pages", (req, res) => {
        res.sendFile(path.join(__dirname, '../dist/index.html'));
    })

    app.get("/pages/*", (req, res) => {
        res.sendFile(path.join(__dirname, '../dist/index.html'));
    })

}
