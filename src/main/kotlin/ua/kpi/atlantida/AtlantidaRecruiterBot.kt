package ua.kpi.atlantida

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.logging.BotLogger
import ua.kpi.atlantida.db.DatabaseManager
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.properties.TelegramProperties
import ua.kpi.atlantida.repository.PretenderRepository
import ua.kpi.atlantida.repository.PretenderRepositoryImpl
import java.io.InvalidObjectException

class AtlantidaRecruiterBot : TelegramLongPollingBot() {

    private companion object {
        const val START_COMMAND = "/start"
        val TAG = AtlantidaRecruiterBot::class.java.simpleName!!
    }

    private val telegramProperties = TelegramProperties()
    private val pretenderRepository: PretenderRepository = PretenderRepositoryImpl(DatabaseManager.getInstance())

    override fun onUpdateReceived(update: Update?) {
        try {
            update?.message?.let {
                if (it.hasText() || it.hasContact()) {
                    try {
                        handleIncomingMessage(it)
                    } catch (e: InvalidObjectException) {
                        BotLogger.severe(TAG, e)
                    }
                }
            }
        } catch (e: Exception) {
            BotLogger.error(TAG, e)
        }
    }

    override fun getBotUsername() = telegramProperties.username

    override fun getBotToken() = telegramProperties.token

    private fun handleIncomingMessage(message: Message) {
        when (message.text) {
            START_COMMAND -> handleStartCommand(message)
            else -> handleUnknownCommand(message)
        }
    }

    private fun handleStartCommand(message: Message) {
        BotLogger.info(TAG, "start command ${message.chatId}:${message.text}")
        val pretender = pretenderRepository.get(message.chatId)
        val emptyPretender = Pretender(chatId = message.chatId)
        if (pretender == null) {
            pretenderRepository.insert(emptyPretender)
        } else {
            pretenderRepository.update(emptyPretender)
        }
        execute(SendMessage(message.chatId, "DEBUG start command received ${message.chatId}:${message.text}"))
    }

    private fun handleUnknownCommand(message: Message) {
        BotLogger.info(TAG, "unknown command: ${message.chatId}:${message.text}")
        execute(SendMessage(message.chatId, "DEBUG unknown command received ${message.chatId}:${message.text}"))
    }

}