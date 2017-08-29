package ua.kpi.atlantida

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage
import ua.kpi.atlantida.properties.TelegramProperties

class AtlantidaBot : TelegramLongPollingBot() {

    private val telegramProperties: TelegramProperties = TelegramProperties()

    override fun getBotToken() = telegramProperties.token

    override fun getBotUsername() = telegramProperties.username

    override fun onUpdateReceived(update: Update?) {
        //check if the update has a message
        if (update?.hasMessage()!!) {
            val message = update.message

            //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
            if (message.hasText()) {

                //create a object that contains the information to send back the message
                val messageText = "you said: ${message.text}"
                val sendMessageRequest = SendMessage(message.chatId, messageText)
                try {
                    sendApiMethod(sendMessageRequest) //at the end, so some magic and send the message ;)
                } catch (e: TelegramApiException) {
                    //do some error handling
                }
            }
        }
    }
}