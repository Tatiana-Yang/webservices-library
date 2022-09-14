module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:' + Number(process.env.PORT) + '/'
            },
            '/spring': {
                target: 'http://localhost:8081/'
            }
        }
    }
}