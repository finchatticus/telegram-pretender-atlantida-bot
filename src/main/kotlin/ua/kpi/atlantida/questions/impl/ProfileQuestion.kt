package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.questions.Question

class ProfileQuestion : Question() {

    override fun requestQuestion() = SendMessage().apply { text = questionProperties.profile }

    override fun checkAnswer(message: Message) = true

    override fun showError() = SendMessage().apply { text = "Profile error" }
}