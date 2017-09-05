package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.PhoneValidator

class PhoneQuestion : Question() {

    private val phoneValidatorComposer: Validator<String> = ValidatorComposer(PhoneValidator(questionProperties.phoneError))

    override fun requestQuestion() = SendMessage().apply { text = questionProperties.phone }

    override fun checkAnswer(message: Message) = phoneValidatorComposer.isValid(message.text)

    override fun showError() = SendMessage().apply { text = phoneValidatorComposer.getDescription() }
}