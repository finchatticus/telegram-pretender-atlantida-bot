package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.questions.Question

class LevelQuestion : Question() {

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.level
        replyMarkup = getLevelsKeyboard()
    }

    override fun checkAnswer(message: Message): Boolean {
        return try {
            message.text.toInt() in 1..6
        } catch (e: NumberFormatException) {
            false
        }
    }

    override fun showError() = SendMessage().apply { text = "Level error" }

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