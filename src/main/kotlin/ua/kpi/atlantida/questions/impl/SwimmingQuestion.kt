package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.questions.Question


class SwimmingQuestion : Question() {

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.swimming
        replyMarkup = getYesNoKeyboard()
    }

    override fun checkAnswer(message: Message) = true

    override fun showError() = SendMessage().apply { text = "Swimming error" }

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