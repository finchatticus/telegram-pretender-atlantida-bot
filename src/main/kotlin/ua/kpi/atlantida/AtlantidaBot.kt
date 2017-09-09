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
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class AtlantidaBot : TelegramLongPollingBot() {

    private companion object {
        const val HOURS_24_MILLIS = 86_400_000
    }

    private val telegramProperties: TelegramProperties = TelegramProperties()
    private val chatHashMap: MutableMap<Long, QuestionManager> = ConcurrentHashMap()
    private val databaseHelper = DataBaseHelper()
    private val scheduler = Executors.newScheduledThreadPool(1).apply {
        scheduleAtFixedRate({ removeChatHashMap() }, 8, 8, TimeUnit.HOURS)
    }

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
                        if (chatHashMap.containsKey(chatId)) {
                            println("chatHashMap.containsKey(chatId)")
                            chatHashMap.remove(chatId)
                        }
                        println("/start")
                        start(chatId)
                    }
                    else -> {
                        if (chatHashMap.containsKey(chatId)) {
                            val questionManager = chatHashMap[chatId]
                            if (questionManager?.isStartCommand!!) {
                                sendReply(questionManager.getResponse(message))
                            }
                        } else {
                            start(chatId)
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

    private fun start(chatId: Long) {
        val questionManager = QuestionManager(chatId).apply { endCallback = { chatId, pretender -> endQuestion(chatId, pretender) } }
        chatHashMap[chatId] = questionManager
        startQuestionManager(questionManager)
    }

    private fun endQuestion(chatId: Long, pretender: Pretender) {
        println("endQuestion chatId $chatId size = ${chatHashMap.size}")
        chatHashMap.remove(chatId)
        println("size = ${chatHashMap.size}")
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

    private fun removeChatHashMap() {
        println("removeChatHashMap size1 = ${chatHashMap.size}")
        chatHashMap.forEach { key, value ->
            if (Math.abs(System.currentTimeMillis() - value.createdAt) > HOURS_24_MILLIS) {
                val pretender = value.pretender
                Thread({ databaseHelper.insertPretender(pretender) }).start()
                chatHashMap -= key
            }
        }
        println("removeChatHashMap size2 = ${chatHashMap.size}")
    }

}