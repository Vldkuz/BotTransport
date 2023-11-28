package Develop.Telegram.Runner;

import Develop.Telegram.RequestHandler;
import Develop.Telegram.SessionHolder.SessionHolder;
import Develop.Telegram.UserHolder.Session;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
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
        if (update.hasMessage() && update.getMessage().hasText()) {

            String chatId = String.valueOf(update.getMessage().getChatId());
            String request = update.getMessage().getText();

            Session curSession = sessionHolder.get(chatId);

            RequestHandler requestHandler = new RequestHandler(curSession);
            if (request.equals("/rs")) {
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setChatId(chatId);
//                sendMessage.setText("Недавние станции");
//
//                InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
//                List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
//                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
//                var codeButton1 = new InlineKeyboardButton();
//                codeButton1.setText("s9600213");
//                codeButton1.setCallbackData("s9600213");
//
//                var codeButton2 = new InlineKeyboardButton();
//                codeButton2.setText("s9600212");
//                codeButton2.setCallbackData("s9600212");
//
//                rowInLine.add(codeButton1);
//                rowInLine.add(codeButton2);
//
//                rowsInLine.add(rowInLine);
//
//                markupInLine.setKeyboard(rowsInLine);
//                sendMessage.setReplyMarkup(markupInLine);
//                try {
//                    execute(sendMessage);
//                } catch (TelegramApiException ignored) {
//                }
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Недавние станции");

                // Создаем клавиатуру
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                message.setReplyMarkup(keyboardMarkup);

                // Создаем список строк клавиатуры
                List<KeyboardRow> keyboard = new ArrayList<>();

                // Создаем строку клавиатуры
                KeyboardRow row = new KeyboardRow();

                // Создаем кнопку
                KeyboardButton button = new KeyboardButton();
                button.setText("dgtrmep");

                // Добавляем кнопку в строку
                row.add(button);

                // Добавляем строку в список
                keyboard.add(row);

                // Привязываем список к клавиатуре
                keyboardMarkup.setKeyboard(keyboard);

                // Включаем автоматическое скрытие клавиатуры после нажатия кнопки
                keyboardMarkup.setOneTimeKeyboard(true);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else {
                Queue<String> answer = requestHandler.getAnswer(request, update, 404.0,404.0);
                sendMessageQueueString(chatId, answer);
            }
        }
        else if (update.hasCallbackQuery()){
            String callbackData = update.getCallbackQuery().getData();
            long messageID = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if(callbackData.equals("s9600213")){
                String text = "s9600213";
                EditMessageText message = new EditMessageText();
                message.setChatId(String.valueOf(chatId));
                message.setText(text);
                message.setMessageId((int)messageID);
                try {
                    execute(message);
                } catch (TelegramApiException ignored) {
                }
            }
            else if (callbackData.equals("s9600212")){
                String text = "s9600212";
                EditMessageText message = new EditMessageText();
                message.setChatId(String.valueOf(chatId));
                message.setText(text);
                message.setMessageId((int)messageID);
                try {
                    execute(message);
                } catch (TelegramApiException ignored) {
                }
            }
        }
        else if(update.hasMessage()) {
            Double latitude = update.getMessage().getLocation().getLatitude();
            Double longitude =  update.getMessage().getLocation().getLongitude();

            String chatId = String.valueOf(update.getMessage().getChatId());
            Session curSession = sessionHolder.get(chatId);
            RequestHandler requestHandler = new RequestHandler(curSession);
            Queue<String> answer = requestHandler.getAnswer("request", update, latitude,longitude);
            sendMessageQueueString(chatId, answer);
        }
    }

    // При каждом присланном сообщение бот заходит в OnUpdateRecieved
    // Получает chat id пользователя и смотрит на состояние в котором находится пользователь
    // Сколько может быть состояний у пользователя ?
    // В зависимости от состояния пользователя вызывается функция с API и происходит парсинг объекта и ответ при помощи бизнес логики
    // Затем это сообщение должно отсылаться телеграмом просто как поток байт, который ничего не знает о бизнес логике

    public void sendMessageQueueString(String chatId, Queue<String> messageQueue) {
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
