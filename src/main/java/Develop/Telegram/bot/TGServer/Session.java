package Develop.Telegram.bot.TGServer;

import Develop.Telegram.bot.StateObject;
import Develop.Telegram.bot.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Session {

    private final String chatId;
    private final String text;
    private final TelegramBot telegramBot;

    public Session(String chatId, String text, TelegramBot telegramBot) {
        this.chatId = chatId;
        this.text = text;
        this.telegramBot = telegramBot;
    }


    public void run(StateObject comand) {
        TGServer tgServer = new TGServer(text);
        sendText(tgServer.run(text, comand));
    }

    private void sendText(String massege) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(massege);
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
