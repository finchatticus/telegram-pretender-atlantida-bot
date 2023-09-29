package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question
import ua.kpi.atlantida.validator.Validator
import ua.kpi.atlantida.validator.ValidatorComposer
import ua.kpi.atlantida.validator.impl.FacultyValidator
import java.util.*

class FacultyQuestion : Question() {

    private val facultyValidatorComposer: Validator<String> = ValidatorComposer(FacultyValidator(
            questionProperties.facultyError,
            questionProperties.faculties))

    override fun requestQuestion(chatId: Long) = SendMessage(chatId.toString(), questionProperties.faculty).apply {
        replyMarkup = getFacultiesKeyboard()
    }

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        return if (message.hasText() && facultyValidatorComposer.isValid(message.text)) {
            pretender.faculty = message.text.trim()
            null
        } else {
            SendMessage(message.chatId.toString(), facultyValidatorComposer.getDescription()).apply {
                replyMarkup = getFacultiesKeyboard()
            }
        }
    }

    private fun getFacultiesKeyboard() = ReplyKeyboardMarkup().apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
        keyboard = ArrayList<KeyboardRow>().apply {
            val faculties = questionProperties.faculties
            for (i in 0 until faculties.size - (faculties.size % 3) step 3) {
                add(KeyboardRow().apply {
                    add(faculties[i])
                    add(faculties[i + 1])
                    add(faculties[i + 2])
                })
            }
            val row = KeyboardRow()
            for (i in (faculties.size - faculties.size % 3)..(faculties.size - 1)) {
                row.add(faculties[i])
            }
            if (row.isNotEmpty()) {
                add(row)
            }
        }
    }
}