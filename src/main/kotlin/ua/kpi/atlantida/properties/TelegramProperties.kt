package ua.kpi.atlantida.properties

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class TelegramProperties {

    private companion object {
        val FILE_NAME = "telegram.keys"
        val PROP_TOKEN = "token"
        val PROP_USERNAME = "username"
    }
    
    val token: String
        get() = readProperties().getProperty(PROP_TOKEN) ?: throw RuntimeException("Token is not set in ${FILE_NAME}")

    val username: String
        get() = readProperties().getProperty(PROP_USERNAME) ?: throw RuntimeException("Username is not set in ${FILE_NAME}")

    private fun writeProperties(props: Properties) {
        FileOutputStream(FILE_NAME).use { output -> props.store(output, null) }
    }

    private fun readProperties() = Properties().apply {
        FileInputStream(FILE_NAME).use { input -> load(input) }
    }
    
}