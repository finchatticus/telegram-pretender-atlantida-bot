package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.SwimmingLevelValidator
import java.util.*

class SwimmingLevelQuestion : Question() {

    private val swimmingLevelValidatorComposer: Validator<String> = ValidatorComposer(SwimmingLevelValidator(
            questionProperties.swimmingLevelError,
            questionProperties.swimmingLevels))

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.swimmingLevel
        replyMarkup = getSwimmingLevelsKeyboard()
    }

    override fun checkAnswer(message: Message) = swimmingLevelValidatorComposer.isValid(message.text)

    override fun showError() = SendMessage().apply {
        text = swimmingLevelValidatorComposer.getDescription()

    }

    private fun getSwimmingLevelsKeyboard() = ReplyKeyboardMarkup().apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
        keyboard = ArrayList<KeyboardRow>(questionProperties.faculties.size).apply {
            val swimmingLevels = questionProperties.swimmingLevels
            for (i in 0 until swimmingLevels.size - (swimmingLevels.size % 3) step 3) {
                add(KeyboardRow().apply {
                    add(swimmingLevels[i])
                    add(swimmingLevels[i + 1])
                    add(swimmingLevels[i + 2])
                })
            }
            val row = KeyboardRow()
            for (i in (swimmingLevels.size - swimmingLevels.size % 3)..(swimmingLevels.size - 1)) {
                row.add(swimmingLevels[i])
            }
            if (row.isNotEmpty()) {
                add(row)
            }
        }
    }
}