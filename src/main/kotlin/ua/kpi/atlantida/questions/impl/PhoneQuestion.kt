package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.questions.Question

class PhoneQuestion : Question() {

    override fun requestQuestion() = SendMessage().apply { text = questionProperties.phone }

    override fun checkAnswer(message: Message) = true

    override fun showError() = SendMessage().apply { text = "Phone error" }
}