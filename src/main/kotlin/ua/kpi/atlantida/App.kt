package ua.kpi.atlantida

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.exceptions.TelegramApiException

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    val botsApi = TelegramBotsApi()
    try {
        botsApi.registerBot(AtlantidaBot())
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }
}