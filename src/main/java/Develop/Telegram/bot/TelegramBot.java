package Develop.Telegram.bot;

import Develop.API.API;
import Develop.Telegram.bot.TGServer.TGServer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "MainTransport_bot";
    }

    @Override
    public String getBotToken() {

       // return "6404015792:AAGtQcDEdeWOBrfpVcXnHzoTq8bm0ZX-dNA";
        String keyValue = "";
        try {
            // Путь к вашему JSON файлу
            String filePath = "src/resources/APITelegramKey.json";

            // Создание объекта ObjectMapper из библиотеки Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Чтение файла и создание JsonNode объекта
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            // Получение значения "Key" из JsonNode объекта
            keyValue = jsonNode.get("Key").asText();
            //api = new API(keyValue/*json_api_key*/, "https://api.rasp.yandex.net/v3.0");
            return keyValue;

            //System.out.println(keyValue); // Выводит "2e07c9e6-4de0-486d-accd-95e725fd87bc"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyValue;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        TGServer.run(chatId,text,sendMessage);
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
