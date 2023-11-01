package Develop.Telegram.bot;

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
            return keyValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyValue;
    }

    private StateObject Comand = new StateObject("");
    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();


        TGServer TGbot = new TGServer(chatId,text,sendMessage);
        TGbot.run(Comand);

//        sendMessage.setChatId(chatId);
//        sendMessage.setText(text);
//        go(sendMessage);
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }



}
