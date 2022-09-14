const passport = require('passport');

// getting the local authentication type
const LocalStrategy = require('passport-local').Strategy;

const bcrypt = require('bcryptjs');

const db = require("../models");
const User = db.user;


passport.use('signIn', new LocalStrategy({ usernameField: "login" }, async (login, password, done) => {
        let user = await User.findOne({ login: login });
        if(user) {
            bcrypt.compare(password, user.password, (err, isMatch) => {
                if (err) throw err;

                if(isMatch) {
                    return done(null, user);
                } else {
                    return done(null, false, { message: "Wrong password" });
                }
            });
        } else {
            return done(null, false, { message: "User not found" });
        }
    })
);




passport.serializeUser((user, done) => {
    done(null, user.id);
});

passport.deserializeUser((id, done) => {
    User.findById(id, (err, user) => {
        done(err, user);
    });
});


module.exports = passport;
