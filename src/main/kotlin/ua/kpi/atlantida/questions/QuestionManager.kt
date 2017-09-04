package ua.kpi.atlantida.questions

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.questions.impl.*

class QuestionManager {

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
    var endCallback: (() -> Unit)? = null

    fun start(): SendMessage {
        currentQuestionIndex = 0
        return questionList[currentQuestionIndex].requestQuestion()
    }

    fun execute(input: Message): SendMessage {
        val currentQuestion = questionList[currentQuestionIndex]
        if (currentQuestionIndex != questionList.size - 1) {
            if (currentQuestion.checkAnswer(input)) {
                return questionList[++currentQuestionIndex].requestQuestion()
            } else {
                return currentQuestion.showError()
            }
        } else {
            endCallback?.let { it() }
            return SendMessage().apply { text = "Thanks" }
        }
    }

}