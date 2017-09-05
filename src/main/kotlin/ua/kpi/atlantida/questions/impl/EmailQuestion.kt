package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.EmailValidator

class EmailQuestion : Question() {

    private val emailValidatorComposer: Validator<String> = ValidatorComposer(EmailValidator(questionProperties.emailError))

    override fun requestQuestion() = SendMessage().apply { text = questionProperties.email }

    override fun checkAnswer(message: Message) = emailValidatorComposer.isValid(message.text)

    override fun showError() = SendMessage().apply { text = emailValidatorComposer.getDescription() }

}