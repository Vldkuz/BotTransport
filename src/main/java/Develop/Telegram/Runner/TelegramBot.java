package Develop.Telegram.Runner;

import Develop.Telegram.RequestHandler;
import Develop.Telegram.SessionHolder.SessionHolder;
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
        String chatId = String.valueOf(update.getMessage().getChatId());
        Session curSession = sessionHolder.get(chatId);
        RequestHandler requestHandler = new RequestHandler(curSession);
        Queue<String> answer = null;

        if (update.getMessage().hasText()) {
            String request = update.getMessage().getText();
            if (!request.equals("/rs")) {
                answer = requestHandler.getAnswer(request, curSession);
            }
            else {
                sendButtonMessage(update.getMessage().getChatId());
                answer = new LinkedList<>();
                answer.add("12");
                curSession.setBlocked(false);
            }
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

    private void sendButtonMessage(Long  chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Недавние станции отображены под Вашей строкой");

        // Создаем клавиатуру
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(keyboardMarkup);
        Session curSession = sessionHolder.get(String.valueOf(chatId));
        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();
        List<String> lastStation = curSession.getInfoHolder().getLastStation(1);
        for (int i = 0; i < 1; i++) {
            // Создаем строку клавиатуры
            KeyboardRow row = new KeyboardRow();

            // Создаем кнопку
            KeyboardButton button = new KeyboardButton();
            button.setText(lastStation.get(i));

            // Добавляем кнопку в строку
            row.add(button);

            // Добавляем строку в список
            keyboard.add(row);
        }

        // Привязываем список к клавиатуре
        keyboardMarkup.setKeyboard(keyboard);

        // Включаем автоматическое скрытие клавиатуры после нажатия кнопки
        keyboardMarkup.setOneTimeKeyboard(true);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageQueueString(String chatId, Queue<String> messageQueue) {
        if (messageQueue == null)
            return;


        while (!messageQueue.isEmpty()) {
            String answer = messageQueue.remove();
            SendMessage sendMessage = new SendMessage(chatId, answer);
            try {
                execute(sendMessage);
            } catch (TelegramApiException ignored) {
            }
        }
    }
}



























//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText("Недавние станции");
//
//        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
//        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
//        var codeButton1 = new InlineKeyboardButton();
//        codeButton1.setText("s9600213");
//        codeButton1.setCallbackData("s9600213");
//
//        var codeButton2 = new InlineKeyboardButton();
//        codeButton2.setText("s9600212");
//        codeButton2.setCallbackData("s9600212");
//
//        rowInLine.add(codeButton1);
//        rowInLine.add(codeButton2);
//
//        rowsInLine.add(rowInLine);
//
//        markupInLine.setKeyboard(rowsInLine);
//        sendMessage.setReplyMarkup(markupInLine);
//        try {
//          execute(sendMessage);
//        } catch (TelegramApiException ignored) {
//        }
//
//      }


















