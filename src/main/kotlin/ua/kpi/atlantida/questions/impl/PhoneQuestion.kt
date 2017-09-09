package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.PhoneValidator

class PhoneQuestion(private val pretender: Pretender) : Question() {

    private val phoneValidatorComposer: Validator<String> = ValidatorComposer(PhoneValidator(questionProperties.phoneError))

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.phone
        replyMarkup = ReplyKeyboardMarkup().apply {
            selective = true
            resizeKeyboard = true
            oneTimeKeyboard = true
            keyboard = ArrayList<KeyboardRow>(1).apply {
                add(KeyboardRow().apply {
                    add(KeyboardButton().apply {
                        text = questionProperties.requestPhone
                        requestContact = true
                    })
                })
            }
        }
    }

    override fun checkAnswer(message: Message): Boolean {
        val phone = message.contact?.phoneNumber
        if (phone != null) {
            pretender.phone = phone
            return true
        } else if (message.hasText()) {
            pretender.phone = message.text.trim()
        }

        if (pretender.phone.isNotBlank()) {
            return phoneValidatorComposer.isValid(pretender.phone)
        }

        return false
    }

    override fun showError() = SendMessage().apply {
        text = phoneValidatorComposer.getDescription()
        replyMarkup = ReplyKeyboardMarkup().apply {
            selective = true
            resizeKeyboard = true
            oneTimeKeyboard = true
            keyboard = ArrayList<KeyboardRow>(1).apply {
                add(KeyboardRow().apply {
                    add(KeyboardButton().apply {
                        text = questionProperties.requestPhone
                        requestContact = true
                    })
                })
            }
        }
    }
}