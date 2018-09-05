package ua.kpi.atlantida.questions

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ua.kpi.atlantida.properties.QuestionProperties

abstract class Question {

    protected val questionProperties = QuestionProperties()

    abstract fun requestQuestion(): SendMessage

    abstract fun checkAnswer(message: Message): Boolean

    abstract fun showError(): SendMessage
}