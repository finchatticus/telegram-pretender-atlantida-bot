package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.LevelValidator

class LevelQuestion(private val pretender: Pretender) : Question() {

    private val levelValidatorComposer: Validator<String> = ValidatorComposer(LevelValidator(
            questionProperties.levelError,
            questionProperties.levels))

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.level
        replyMarkup = getLevelsKeyboard()
    }

    override fun checkAnswer(message: Message): Boolean {
        if (message.hasText() && levelValidatorComposer.isValid(message.text)) {
            pretender.level = message.text.trim()
            return true
        }
        return false
    }

    override fun showError() = SendMessage().apply {
        text = levelValidatorComposer.getDescription()
        replyMarkup = getLevelsKeyboard()
    }

    private fun getLevelsKeyboard() = ReplyKeyboardMarkup().apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
        keyboard = ArrayList<KeyboardRow>(questionProperties.faculties.size).apply {
            val levels = questionProperties.levels
            for (i in 0 until levels.size - (levels.size % 3) step 3) {
                add(KeyboardRow().apply {
                    add(levels[i])
                    add(levels[i + 1])
                    add(levels[i + 2])
                })
            }
            val row = KeyboardRow()
            for (i in (levels.size - levels.size % 3)..(levels.size - 1)) {
                row.add(levels[i])
            }
            if (row.isNotEmpty()) {
                add(row)
            }
        }
    }
}