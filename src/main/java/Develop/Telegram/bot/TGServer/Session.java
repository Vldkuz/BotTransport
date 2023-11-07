package Develop.Telegram.bot.TGServer;

import Develop.Telegram.bot.StateObject;
import Develop.Telegram.bot.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private final String chatId;
    private final String text;
    private final TelegramBot telegramBot;

    public Session(String chatId, String text, TelegramBot telegramBot) {
        this.chatId = chatId;
        this.text = text;
        this.telegramBot = telegramBot;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/h", "help"));
        listofCommands.add(new BotCommand("/bs", "Расписание рейсов по станции"));
        listofCommands.add(new BotCommand("/lot", "Список станций следования"));
        listofCommands.add(new BotCommand("/fs", "Расписание рейсов между станциями"));
        listofCommands.add(new BotCommand("/ns", "Список ближайших станций"));
        listofCommands.add(new BotCommand("/nc", "Ближайший город"));
        listofCommands.add(new BotCommand("/ci", "Информация о перевозчике"));
        try {
            telegramBot.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        }
        catch (TelegramApiException apiException){
            System.out.println(apiException.getMessage());
        }
    }


    public void run(StateObject comand) {
        TGServer tgServer = new TGServer(chatId,text);
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








/*
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
        TGServer tgServer = new TGServer(chatId,);
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
*/
