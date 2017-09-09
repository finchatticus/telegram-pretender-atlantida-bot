package ua.kpi.atlantida

import org.telegram.telegrambots.api.methods.BotApiMethod
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import ua.kpi.atlantida.db.DataBaseHelper
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.properties.TelegramProperties
import ua.kpi.atlantida.questions.QuestionManager

class AtlantidaBot : TelegramLongPollingBot() {

    private val telegramProperties: TelegramProperties = TelegramProperties()
    private val chatHashmap: MutableMap<Long, QuestionManager> = HashMap()
    private val databaseHelper = DataBaseHelper()

    override fun getBotToken() = telegramProperties.token

    override fun getBotUsername() = telegramProperties.username

    override fun onUpdateReceived(update: Update?) {
        if (update?.hasMessage()!!) {
            val message = update.message
            val chatId = message.chatId
            val updateId = update.updateId

            if (message.hasText() || message.contact != null) {
                println("message chatId = $chatId, updateId = $updateId, text = ${message.text}")
                when (message.text) {
                    "/start" -> {
                        if (chatHashmap.containsKey(chatId)) {
                            println("chatHashmap.containsKey(chatId)")
                            chatHashmap.remove(chatId)
                        }
                        println("/start")
                        val questionManager = QuestionManager(chatId).apply { endCallback = { chatId, pretender -> endQuestion(chatId, pretender) } }
                        chatHashmap[chatId] = questionManager
                        startQuestionManager(questionManager)
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
        println("sendReply message chatId")
        try {
            sendApiMethod(message)
        } catch (e: TelegramApiException) {
            println("sendReply ex: ${e.printStackTrace()}")
        }
    }

    private fun endQuestion(chatId: Long, pretender: Pretender) {
        println("endQuestion chatId $chatId size = ${chatHashmap.size}")
        chatHashmap.remove(chatId)
        println("size = ${chatHashmap.size}")
        Thread({ databaseHelper.insertPretender(pretender) }).start()
    }

    private fun startQuestionManager(questionManager: QuestionManager?) {
        println("startQuestionManager")
        if (questionManager != null) {
            println("startQuestionManager questionManager != null")
            println("startQuestionManager questionManager.isStartCommand = ${questionManager.isStartCommand}")
            if (!questionManager.isStartCommand) {
                questionManager.isStartCommand = true
                questionManager.start()
                sendReply(questionManager.requestQuestion())
            }
        }
    }

}