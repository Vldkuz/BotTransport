package Develop.Telegram.Runner;

import Develop.Telegram.RequestHandler;
import Develop.Telegram.SessionHolder.SessionHolder;
import Develop.Telegram.UserHolder.InfoHolder;
import Develop.Telegram.UserHolder.Session;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.System.getenv;

public class TelegramBot extends TelegramLongPollingBot {

    private final String keyTelegram = getenv("KEY_TELEGRAM");
    private final SessionHolder sessionHolder = new SessionHolder(getenv("KEY_YANDEX"));

    @Override
    public String getBotUsername() {
        return "MainTransport_bot";
    }

    @Override
    public String getBotToken() {
        return keyTelegram;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        String chatId = String.valueOf(update.getMessage().getChatId());
        Session curSession = sessionHolder.get(chatId);
        RequestHandler requestHandler = new RequestHandler(curSession);
        Queue<SendMessage> answer = null;

        if (update.getMessage().hasText()) {
            String request = update.getMessage().getText();
            answer = requestHandler.getAnswer(request, curSession);
        }

        if (update.getMessage().hasLocation()) {
            Location locale = update.getMessage().getLocation();
            answer = requestHandler.getAnswer(locale);
        }

        sendMessageQueueString(chatId, answer);
    }


    // При каждом присланном сообщение бот заходит в OnUpdateRecieved
    // Получает chat id пользователя и смотрит на состояние в котором находится пользователь
    // Сколько может быть состояний у пользователя ?
    // В зависимости от состояния пользователя вызывается функция с API и происходит парсинг объекта и ответ при помощи бизнес логики
    // Затем это сообщение должно отсылаться телеграмом просто как поток байт, который ничего не знает о бизнес логике
    public void sendMessageQueueString(String chatId, Queue<SendMessage> messageQueue) {
        if (messageQueue == null)
            return;

        while (!messageQueue.isEmpty()) {
            SendMessage sendMessage = messageQueue.remove();
            sendMessage.setChatId(chatId);
            if (sendMessage.getText() == null)
                continue;
            try {
                execute(sendMessage);
            } catch (TelegramApiException ignored) {
            }
        }
    }
}














