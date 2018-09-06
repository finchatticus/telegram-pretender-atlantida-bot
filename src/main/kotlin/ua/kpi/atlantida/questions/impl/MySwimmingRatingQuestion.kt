package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.MySwimmingLevelValidator
import java.util.*

/**
 * Created by vlad on 06.09.2018
 */
class MySwimmingRatingQuestion : Question() {

    private val mySwimmingLevelValidatorComposer: Validator<String> = ValidatorComposer(MySwimmingLevelValidator(
            questionProperties.swimmingLevelError,
            questionProperties.swimmingRatings))

    override fun requestQuestion(chatId: Long) = SendMessage(chatId, questionProperties.swimmingRating).apply {
        replyMarkup = getSwimmingRatingsKeyboard()
    }

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        return if (message.hasText() && mySwimmingLevelValidatorComposer.isValid(message.text)) {
            pretender.mySwimmingRating = message.text.trim()
            null
        } else {
            SendMessage(message.chatId, mySwimmingLevelValidatorComposer.getDescription()).apply {
                replyMarkup = getSwimmingRatingsKeyboard()
            }
        }
    }

    private fun getSwimmingRatingsKeyboard() = ReplyKeyboardMarkup().apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
        keyboard = ArrayList<KeyboardRow>().apply {
            val swimmingRatings = questionProperties.swimmingRatings
            for (i in 0 until swimmingRatings.size - (swimmingRatings.size % 3) step 3) {
                add(KeyboardRow().apply {
                    add(swimmingRatings[i])
                    add(swimmingRatings[i + 1])
                    add(swimmingRatings[i + 2])
                })
            }
            val row = KeyboardRow()
            for (i in (swimmingRatings.size - swimmingRatings.size % 3)..(swimmingRatings.size - 1)) {
                row.add(swimmingRatings[i])
            }
            if (row.isNotEmpty()) {
                add(row)
            }
        }
    }
}