const express = require("express");
require('dotenv').config();
const configure = require('./configure');

// Swagger
const swaggerJSDoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');
const swaggerDefinition = require('./swagger.json');
const options = {
    swaggerDefinition,
    apis: [`${__dirname}/routes/**/*.js`],
};
const swaggerSpec = swaggerJSDoc(options);

const app = express();

configure(app);

app.use(
    '/docs',
    swaggerUi.serve,
    swaggerUi.setup(swaggerSpec, { explorer: true})
);

// set port, listen for requests
app.listen(Number(process.env.PORT), () => {
    console.log(`Server is running on port ${Number(process.env.PORT)}.`);
});