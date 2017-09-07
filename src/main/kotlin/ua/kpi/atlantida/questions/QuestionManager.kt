package ua.kpi.atlantida.questions

import org.telegram.telegrambots.api.methods.BotApiMethod
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.impl.*

class QuestionManager(private val chatId: Long) {

    companion object {
        private const val SWIMMING_QUESTION_INDEX = 4 // GOVNOKOD MUST BE CHANGE IF NEW QUESTION ADDED
    }

    private var pretender = Pretender()
    private val questionList: MutableList<Question> = mutableListOf()
    private var currentQuestionIndex = 0
    var endCallback: ((chatId: Long, pretender: Pretender) -> Unit)? = null
    var isStartCommand = false

    fun start() {
        currentQuestionIndex = 0
        pretender = Pretender()
        questionList.addAll(listOf(
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
        ))
    }

    fun requestQuestion(): BotApiMethod<Message> = questionList[currentQuestionIndex].requestQuestion().apply { chatId = this@QuestionManager.chatId.toString() }

    fun getResponse(input: Message): BotApiMethod<Message> {
        if (currentQuestionIndex < questionList.size) {
            val currentQuestion = questionList[currentQuestionIndex]
            if (currentQuestion.checkAnswer(input)) {
                if (currentQuestionIndex < questionList.size - 1) {
                    currentQuestionIndex++
                    if (currentQuestionIndex == SWIMMING_QUESTION_INDEX && pretender.swimming == "Ні") currentQuestionIndex++
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
        println("end petender $pretender")
        endCallback?.invoke(chatId, pretender)
        return SendMessage().apply {
            chatId = this@QuestionManager.chatId.toString()
            text = "Дякуємо, Ваша заявка прийнята. Очікуйте запрошення на ознайомчу лекцію."
        }
    }

}
