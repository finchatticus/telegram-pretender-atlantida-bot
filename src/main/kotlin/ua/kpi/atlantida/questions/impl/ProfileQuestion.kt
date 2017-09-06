package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question

class ProfileQuestion(private val pretender: Pretender) : Question() {

    override fun requestQuestion() = SendMessage().apply { text = questionProperties.profile }

    override fun checkAnswer(message: Message): Boolean {
        pretender.profile = message.text
        return true
    }

    override fun showError() = SendMessage().apply { text = "Profile error" }
}