package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.NameValidator

class NameQuestion(private val pretender: Pretender) : Question() {

    private val nameValidatorComposer: Validator<String> = ValidatorComposer(NameValidator(questionProperties.nameError))

    override fun requestQuestion() = SendMessage().apply { text = questionProperties.name }

    override fun checkAnswer(message: Message): Boolean {
        if (message.hasText() && nameValidatorComposer.isValid(message.text)) {
            pretender.name = message.text.trim()
            return true
        }
        return false
    }

    override fun showError() = SendMessage().apply { text = questionProperties.nameError }
}