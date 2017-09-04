package ua.kpi.atlantida

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import ua.kpi.atlantida.properties.TelegramProperties
import ua.kpi.atlantida.questions.QuestionManager

class AtlantidaBot : TelegramLongPollingBot() {

    private val telegramProperties: TelegramProperties = TelegramProperties()
    private val questionManager = QuestionManager().apply { endCallback = { endQuestion() } }
    private var isStartCommand = false

    override fun getBotToken() = telegramProperties.token

    override fun getBotUsername() = telegramProperties.username

    override fun onUpdateReceived(update: Update?) {
        if (update?.hasMessage()!!) {
            val message = update.message

            if (message.hasText()) {

                when (message.text) {
                    "/start" -> {
                        if (!isStartCommand) {
                            isStartCommand = true
                            sendReply(message.chatId, questionManager.start())
                        }
                    }
                    else -> {
                        if (isStartCommand) {
                            sendReply(message.chatId, questionManager.execute(message))
                        }
                    }
                }
            }
        }
    }

    private fun sendReply(chatId: Long, sendMessage: SendMessage) {
        sendMessage.apply {
            this.chatId = chatId.toString()
        }
        try {
            sendApiMethod(sendMessage)
        } catch (e: TelegramApiException) {
            //do some error handling
        }
    }

    private fun endQuestion() {
        isStartCommand = false
    }

}