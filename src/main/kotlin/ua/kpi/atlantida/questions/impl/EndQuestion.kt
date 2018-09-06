package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.Question

/**
 * Created by vlad on 06.09.2018
 */
class EndQuestion : Question() {

    override fun requestQuestion(chatId: Long) = SendMessage(chatId, "Дякуємо, Ваша заявка прийнята. Очікуйте запрошення на ознайомчу лекцію.")

    override fun handleAnswer(message: Message, pretender: Pretender): SendMessage? {
        return SendMessage(message.chatId, "Дякуємо, Ваша заявка прийнята. Очікуйте запрошення на ознайомчу лекцію.")
    }

}