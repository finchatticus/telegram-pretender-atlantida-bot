package ua.kpi.atlantida

import org.telegram.telegrambots.api.methods.BotApiMethod
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import ua.kpi.atlantida.properties.TelegramProperties
import ua.kpi.atlantida.questions.QuestionManager

class AtlantidaBot : TelegramLongPollingBot() {

    private val telegramProperties: TelegramProperties = TelegramProperties()
    private val chatHashmap: MutableMap<Long, QuestionManager> = HashMap()

    override fun getBotToken() = telegramProperties.token

    override fun getBotUsername() = telegramProperties.username

    override fun onUpdateReceived(update: Update?) {
        if (update?.hasMessage()!!) {
            val message = update.message
            val chatId = message.chatId

            if (message.hasText()) {

                when (message.text) {
                    "/start" -> {
                        if (chatHashmap.containsKey(chatId)) {
                            val questionManager = chatHashmap[chatId]
                            startQuestionManager(questionManager)
                        } else {
                            val questionManager = QuestionManager(chatId).apply { endCallback = { endQuestion(it) } }
                            chatHashmap[chatId] = questionManager
                            startQuestionManager(questionManager)
                        }
                    }
                    else -> {
                        if (chatHashmap.containsKey(chatId)) {
                            val questionManager = chatHashmap[chatId]
                            if (questionManager?.isStartCommand!!) {
                                sendReply(questionManager.getResponse(message))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun sendReply(message: BotApiMethod<Message>) {
        try {
            sendApiMethod(message)
        } catch (e: TelegramApiException) {
            //do some error handling
        }
    }

    private fun endQuestion(chatId: Long) {
        chatHashmap.remove(chatId)
    }

    private fun startQuestionManager(questionManager: QuestionManager?) {
        if (questionManager != null) {
            if (!questionManager.isStartCommand) {
                questionManager.isStartCommand = true
                questionManager.start()
                sendReply(questionManager.requestQuestion())
            }
        }
    }

}