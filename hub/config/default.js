const baseUrl = process.env.URL || 'http://localhost'
const apiUrl = 'http://localhost'

module.exports = {
    url: baseUrl,
    port: process.env.PORT || 5050,
    apiUrl
}
