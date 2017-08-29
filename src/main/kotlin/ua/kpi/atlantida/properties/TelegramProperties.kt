package ua.kpi.atlantida.properties

class TelegramProperties : MyProperties(FILE_NAME) {

    private companion object {
        private val FILE_NAME = "telegram.keys"
        private val PROP_TOKEN = "token"
        private val PROP_USERNAME = "username"
    }
    
    val token: String
        get() = readProperties().getProperty(PROP_TOKEN) ?: throw RuntimeException("Token is not set in ${FILE_NAME}")

    val username: String
        get() = readProperties().getProperty(PROP_USERNAME) ?: throw RuntimeException("Username is not set in ${FILE_NAME}")
    
}