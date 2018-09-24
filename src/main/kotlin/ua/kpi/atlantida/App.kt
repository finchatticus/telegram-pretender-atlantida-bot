package ua.kpi.atlantida

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.meta.logging.BotLogger
import org.telegram.telegrambots.meta.logging.BotsFileHandler
import ua.kpi.atlantida.db.DatabaseManager
import ua.kpi.atlantida.model.Pretender
import java.io.IOException
import java.util.logging.ConsoleHandler
import java.util.logging.Level

private const val TAG = "APP"

fun main(args: Array<String>) {
    BotLogger.setLevel(Level.ALL)
    try {
        BotLogger.registerLogger(BotsFileHandler())
    } catch (e: IOException) {
        BotLogger.severe(TAG, e)
    }
    try {
        ApiContextInitializer.init()
        val botsApi = TelegramBotsApi()
        try {
            botsApi.registerBot(AtlantidaRecruiterBot())
        } catch (e: TelegramApiException) {
            BotLogger.error(TAG, e)
        }
    } catch (e: Exception) {
        BotLogger.error(TAG, e)
    }
}