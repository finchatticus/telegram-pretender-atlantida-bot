package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.EmailValidator

class EmailQuestion : Question() {

    private val emailValidatorComposer: Validator<String> = ValidatorComposer(EmailValidator(questionProperties.emailError))

    override fun requestQuestion(chatId: Long) = SendMessage(chatId, questionProperties.email).apply {
        replyMarkup = ReplyKeyboardRemove()
    }

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        return if (message.hasText() && emailValidatorComposer.isValid(message.text)) {
            pretender.email = message.text.trim()
            null
        } else {
            SendMessage(message.chatId, emailValidatorComposer.getDescription())
        }
    }

}