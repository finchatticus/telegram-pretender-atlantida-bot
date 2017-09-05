package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.SwimmingValidator


class SwimmingQuestion : Question() {

    private val swimmingValidatorComposer: Validator<String> = ValidatorComposer(SwimmingValidator(
            questionProperties.swimmingError,
            listOf(questionProperties.yes, questionProperties.no)))

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.swimming
        replyMarkup = getYesNoKeyboard()
    }

    override fun checkAnswer(message: Message) = swimmingValidatorComposer.isValid(message.text)

    override fun showError() = SendMessage().apply { text = swimmingValidatorComposer.getDescription() }

    private fun getYesNoKeyboard() = ReplyKeyboardMarkup().apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
        keyboard = ArrayList<KeyboardRow>(2).apply {
            add(KeyboardRow().apply {
                add(questionProperties.yes)
                add(questionProperties.no)
            })
        }
    }
}