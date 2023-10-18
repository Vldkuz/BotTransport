package Develop.Telegram.bot.TGServer;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.PrintWriter;

public class TGServer {

    public static void run(String chatId, String text, SendMessage sendMessage) {
//        if (text.equals("negr"))
//        {
//            sendMessage.setChatId(chatId);
//            sendMessage.setText("Hi Vlad");
//        }
//        else
//        {
//            sendMessage.setChatId(chatId);
//            sendMessage.setText(text);
//        }
        String userString = text;
        switch (userString) {
            case "-help":       //help
            case "-h":
            case "help":
            case "h":
                sendMessage.setChatId(chatId);
                sendMessage.setText(help());
                break;
            case "fs":               //Расписание рейсов между станциями
                sendMessage.setChatId(chatId);
                sendMessage.setText(showScheduleBetStation());

                break;
            case "bs":               //Расписание рейсов по станции
                sendMessage.setChatId(chatId);
                sendMessage.setText(showScheduleByStation());

                break;
            case "lot":               //Список станций следования
                sendMessage.setChatId(chatId);
                sendMessage.setText(showFollowStations());

                break;
            case "ns":               //Список ближайших станций
                sendMessage.setChatId(chatId);
                sendMessage.setText(showNearStation());

                break;
            case "nc":               //Ближайший город
                sendMessage.setChatId(chatId);
                sendMessage.setText(showNeartCity());

                break;
            case "ci":               //Информация о перевозчике
                sendMessage.setChatId(chatId);
                sendMessage.setText(showInfCarrier());

                break;
            default:
                sendMessage.setChatId(chatId);
                sendMessage.setText("Not have this command. try write key(-h) or white (-exit), if you want to leave");
                break;
        }

    }

    private static String help() {
        return "The help code";
    }

    private static String showScheduleByStation(/*PrintWriter writer, BufferedReader reader*/) /*throws IOException*/ {
        return ("Schedule by station");
    }

    private static String showScheduleBetStation() {
        return "Schedule between stations";
    }

    private static String showFollowStations() {
        return "Follow stations";
    }

    private static String showNearStation() {
        return "List of nearest stations";
    }

    private static String showNeartCity() {
        return "Nearest city";
    }

    private static String showInfCarrier() {
        return "Information about the carrier";
    }
}
