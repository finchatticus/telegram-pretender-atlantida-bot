package ua.kpi.atlantida

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

private const val TAG = "APP"

fun main(args: Array<String>) {
    /*BotLogger.setLevel(Level.ALL)
    try {
        BotLogger.registerLogger(BotsFileHandler())
    } catch (e: IOException) {
        BotLogger.severe(TAG, e)
    }
    try {
        ApiContextInitializer.init()
        val botsApi = TelegramBotsApi()
        try {
            botsApi.registerBot(())
        } catch (e: TelegramApiException) {
            BotLogger.error(TAG, e)
        }
    } catch (e: Exception) {
        BotLogger.error(TAG, e)
    }*/
    try {
        val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
        botsApi.registerBot(AtlantidaRecruiterBot())
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }

}