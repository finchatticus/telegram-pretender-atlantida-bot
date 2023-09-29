package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question

class MarketingQuestion : Question() {

    override fun requestQuestion(chatId: Long) = SendMessage(chatId.toString(), questionProperties.marketing).apply {
        replyMarkup = getMarketingOptionsKeyboard()
    }

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        return if (message.hasText()) {
            pretender.marketing = message.text.trim()
            null
        } else {
            SendMessage(message.chatId.toString(), questionProperties.error).apply {
                replyMarkup = getMarketingOptionsKeyboard()
            }
        }
    }

    private fun getMarketingOptionsKeyboard() = ReplyKeyboardMarkup().apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
        keyboard = ArrayList<KeyboardRow>(questionProperties.faculties.size).apply {
            questionProperties.marketingOptions.forEach {
                add(KeyboardRow().apply {
                    add(it)
                })
            }
        }
    }

}