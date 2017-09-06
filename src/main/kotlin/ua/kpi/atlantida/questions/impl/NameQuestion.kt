package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question

class NameQuestion(private val pretender: Pretender) : Question() {

    override fun requestQuestion() = SendMessage().apply { text = questionProperties.name }

    override fun checkAnswer(message: Message): Boolean {
        if (message.hasText()) {
            pretender.name = message.text
            return true
        }
        return false
    }

    override fun showError() = SendMessage().apply { text = "Error" }
}