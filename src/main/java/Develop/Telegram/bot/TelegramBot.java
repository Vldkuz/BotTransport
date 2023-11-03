package Develop.Telegram.bot;

import Develop.KeyManager.KeyManager;
import Develop.Main;
import Develop.Telegram.bot.TGServer.Session;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TelegramBot extends TelegramLongPollingBot {

    private StateObject Comand = new StateObject("");

    @Override
    public String getBotUsername() {
        return "MainTransport_bot";
    }

    @Override
    public String getBotToken() {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = Main.class.getResourceAsStream("/APITelegramKey.json");
        KeyManager keyAPI = null;
        try {
            keyAPI = objectMapper.readValue(inputStream, KeyManager.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String keyValue = keyAPI.getKey();
        return keyValue;
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
