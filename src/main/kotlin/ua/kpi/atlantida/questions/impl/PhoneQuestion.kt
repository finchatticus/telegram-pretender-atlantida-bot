package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.PhoneValidator

class PhoneQuestion : Question() {

    private val phoneValidatorComposer: Validator<String> = ValidatorComposer(PhoneValidator(questionProperties.phoneError))

    override fun requestQuestion(chatId: Long) = SendMessage(chatId, questionProperties.phone).apply {
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

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        val phone = message.contact?.phoneNumber
        return if (phone != null) {
            pretender.phone = phone
            null
        } else if (message.hasText() && phoneValidatorComposer.isValid(message.text)) {
            pretender.phone = message.text.trim()
            null
        } else {
            SendMessage(message.chatId, phoneValidatorComposer.getDescription()).apply {
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
    }

}