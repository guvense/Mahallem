const baseUrl = process.env.URL || 'http://localhost'
const apiUrl = 'http://mobile.api:80/api'

module.exports = {
    url: baseUrl,
    port: process.env.PORT || 5050,
    apiUrl
}
