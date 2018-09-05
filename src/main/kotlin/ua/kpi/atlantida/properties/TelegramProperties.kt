package ua.kpi.atlantida.properties

class TelegramProperties : MyProperties(FILE_NAME) {

    private companion object {
        const val FILE_NAME = "telegram.keys"
        const val PROP_TOKEN = "token"
        const val PROP_USERNAME = "username"
    }

    val token = readProperties(PROP_TOKEN)
    val username = readProperties(PROP_USERNAME)
    
}