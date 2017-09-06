package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.PhoneValidator

class PhoneQuestion(private val pretender: Pretender) : Question() {

    private val phoneValidatorComposer: Validator<String> = ValidatorComposer(PhoneValidator(questionProperties.phoneError))

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.phone
    }

    override fun checkAnswer(message: Message): Boolean {
        val phone = message.contact?.phoneNumber
        val text = message.text
        if (phone != null) {
            pretender.phone = phone
            return true
        } else if (text != null) {
            pretender.phone = text
        }

        if (pretender.phone.isNotBlank()) {
            return phoneValidatorComposer.isValid(pretender.phone)
        }

        return false
    }

    override fun showError() = SendMessage().apply { text = phoneValidatorComposer.getDescription() }
}