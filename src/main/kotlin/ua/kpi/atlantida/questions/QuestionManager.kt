package ua.kpi.atlantida.questions

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.questions.impl.*

class QuestionManager(private val chatId: Long) {

    private val questionList: List<Question> = listOf(
            NameQuestion(),
            LevelQuestion(),
            FacultyQuestion(),
            SwimmingQuestion(),
            SwimmingLevelQuestion(),
            PhoneQuestion(),
            EmailQuestion(),
            ProfileQuestion(),
            MotivationQuestion(),
            MarketingQuestion()
    )
    private var currentQuestionIndex = 0
    var endCallback: ((chatId: Long) -> Unit)? = null
    var isStartCommand = false

    fun start(): SendMessage {
        currentQuestionIndex = 0
        return questionList[currentQuestionIndex].requestQuestion().apply { chatId = this@QuestionManager.chatId.toString() }
    }

    fun execute(input: Message): SendMessage {
        val currentQuestion = questionList[currentQuestionIndex]
        return if (currentQuestionIndex != questionList.size - 1) {
            if (currentQuestion.checkAnswer(input)) {
                questionList[++currentQuestionIndex].requestQuestion().apply { chatId = this@QuestionManager.chatId.toString() }
            } else {
                currentQuestion.showError().apply { chatId = this@QuestionManager.chatId.toString() }
            }
        } else {
            endCallback?.invoke(chatId)
            SendMessage().apply {
                chatId = this@QuestionManager.chatId.toString()
                text = "Thanks"
            }
        }
    }

}