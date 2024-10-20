package ua.kpi.atlantida

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import ua.kpi.atlantida.db.DatabaseManager
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.properties.TelegramProperties
import ua.kpi.atlantida.questions.QuestionFactory
import ua.kpi.atlantida.repository.PretenderRepository
import ua.kpi.atlantida.repository.PretenderRepositoryImpl
import java.io.InvalidObjectException

class AtlantidaRecruiterBot : TelegramLongPollingBot() {

    private companion object {
        const val START_COMMAND = "/start"
    }

    private val telegramProperties = TelegramProperties()
    private val pretenderRepository: PretenderRepository = PretenderRepositoryImpl(DatabaseManager.getInstance())
    private val questionFactory = QuestionFactory()

    override fun onUpdateReceived(update: Update?) {
        try {
            update?.message?.let {
                if (it.hasText() || it.hasContact()) {
                    try {
                        handleIncomingMessage(it)
                    } catch (e: InvalidObjectException) {
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getBotUsername() = telegramProperties.username

    override fun getBotToken() = telegramProperties.token

    private fun handleIncomingMessage(message: Message) = GlobalScope.launch(Dispatchers.IO) {
        when (message.text) {
            START_COMMAND -> handleStartCommand(message)
            else -> handleUnknownCommand(message)
        }
    }

    private fun handleStartCommand(message: Message) {
        val pretender = pretenderRepository.get(message.chatId)
        val emptyPretender = Pretender(chatId = message.chatId)
        trySetPretenderProfile(message.from, emptyPretender)
        if (pretender == null) {
            pretenderRepository.insert(emptyPretender)
        } else {
            pretenderRepository.update(emptyPretender)
        }
        val question = questionFactory.create(emptyPretender)
        execute(question.requestQuestion(message.chatId))
    }

    private fun handleUnknownCommand(message: Message) {
        val pretender = pretenderRepository.get(message.chatId)
        if (pretender == null) {
            val newPretender = Pretender(chatId = message.chatId)
            trySetPretenderProfile(message.from, newPretender)
            pretenderRepository.insert(newPretender)
            val question = questionFactory.create(newPretender)
            execute(question.requestQuestion(message.chatId))
        } else {
            val question = questionFactory.create(pretender)
            val errorResponse = question.handleAnswer(message, pretender)
            if (errorResponse == null) {
                pretenderRepository.update(pretender)
                val nextQuestion = questionFactory.create(pretender)
                execute(nextQuestion.requestQuestion(message.chatId))
            }  else {
                execute(errorResponse)
            }
        }
    }

    private fun trySetPretenderProfile(user: User, pretender: Pretender) {
        user.userName?.let {
            pretender.profile = "@$it"
        }
    }

}