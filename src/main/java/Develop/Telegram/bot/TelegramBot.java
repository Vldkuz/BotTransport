package Develop.Telegram.bot;

import Develop.KeyManager.KeyManager;
import Develop.Main;
import Develop.Telegram.bot.TGServer.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.io.InputStream;

public class TelegramBot extends TelegramLongPollingBot {

    KeyManager keyHolder = new KeyManager("/APITelegramKey");
    private StateObject Comand = new StateObject("");

    @Override
    public String getBotUsername() {
        return "MainTransport_bot";
    }

    @Override
    public String getBotToken() {
        return keyHolder.getKey();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();
            Session session = new Session(chatId, text,this);
            session.run(Comand);
        }
    }


}
