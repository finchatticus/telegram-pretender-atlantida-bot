package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.NameValidator

class NameQuestion : Question() {

    override fun requestQuestion(chatId: Long) = SendMessage(chatId, questionProperties.name)

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        return if (message.hasText()) {
            pretender.name = message.text.trim()
            null
        } else {
            SendMessage(message.chatId, questionProperties.nameError)
        }
    }

}