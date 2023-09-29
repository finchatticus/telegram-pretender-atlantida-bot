package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question

class NameQuestion : Question() {

    override fun requestQuestion(chatId: Long) = SendMessage(chatId.toString(), questionProperties.name)

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        return if (message.hasText()) {
            pretender.name = message.text.trim()
            null
        } else {
            SendMessage(message.chatId.toString(), questionProperties.nameError)
        }
    }

}