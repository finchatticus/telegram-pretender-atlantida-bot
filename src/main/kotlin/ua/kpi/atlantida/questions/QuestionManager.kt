package ua.kpi.atlantida.questions

import org.telegram.telegrambots.api.methods.BotApiMethod
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.impl.*

class QuestionManager(private val chatId: Long) {

    private val pretender = Pretender()
    private val questionList: List<Question> = listOf(
            NameQuestion(pretender),
            LevelQuestion(pretender),
            FacultyQuestion(pretender),
            SwimmingQuestion(pretender),
            SwimmingLevelQuestion(pretender),
            PhoneQuestion(pretender),
            EmailQuestion(pretender),
            ProfileQuestion(pretender),
            MotivationQuestion(pretender),
            MarketingQuestion(pretender)
    )
    private var currentQuestionIndex = 0
    var endCallback: ((chatId: Long) -> Unit)? = null
    var isStartCommand = false

    fun start() {
        currentQuestionIndex = 0
    }

    fun requestQuestion(): BotApiMethod<Message> = questionList[currentQuestionIndex].requestQuestion().apply { chatId = this@QuestionManager.chatId.toString() }

    fun getResponse(input: Message): BotApiMethod<Message> {
        if (currentQuestionIndex < questionList.size) {
            val currentQuestion = questionList[currentQuestionIndex]
            if (currentQuestion.checkAnswer(input)) {
                if (currentQuestionIndex < questionList.size - 1) {
                    currentQuestionIndex++
                    return requestQuestion()
                } else {
                    return end()
                }
            } else {
                return currentQuestion.showError().apply { chatId = this@QuestionManager.chatId.toString() }
            }
        } else {
            return end()
        }
    }

    private fun end(): BotApiMethod<Message> {
        endCallback?.invoke(chatId)
        return SendMessage().apply {
            chatId = this@QuestionManager.chatId.toString()
            text = "Thanks"
        }
    }

}