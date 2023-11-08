package Develop.Telegram.bot.TGServer;

import Develop.API.API;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.ParamBuilder;
import Develop.KeyManager.KeyManager;
import Develop.Main;
import Develop.Telegram.bot.MessageStorage;
import Develop.Telegram.bot.StateObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.io.InputStream;

public class TGServer {

    private final API api;

    private String chatId;
    private String text;
    private SendMessage sendMessage;


    public TGServer(String chatId, String text) {
        this.chatId = chatId;
        this.text = text;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Main.class.getResourceAsStream("/APISheduleKey.json");
            KeyManager keyAPI = objectMapper.readValue(inputStream, KeyManager.class);

            String keyValue = keyAPI.getKey();
            this.api = new API(keyValue, "https://api.rasp.yandex.net/v3.0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String help() {
        return "The help code";
    }

    private static String hisoryOfmaeesge(MessageStorage messageStorage) {
        return messageStorage.getAllMessages();
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

    public String run(String text, StateObject comand, MessageStorage messageStorage) {
        if (comand.getStatus().equals("bs")) {
            comand.setStatus("");
            return showScheduleByStation(messageStorage);
        } else {
            switch (text) {
                case "h": //help
                case "/h": //help
                    return help();

                case "bs":               //Расписание рейсов по станции
                case "/bs":               //Расписание рейсов по станции
                    comand.setStatus("bs");
                    return ("Write code of city, please ");

                case "rs":               //Недавние станции
                case "/rs":              //Recent stations
                    return (hisoryOfmaeesge(messageStorage));

                case "lot":               //Список станций следования
                case "/lot":               //Список станций следования
                    return (showFollowStations());


                case "fs":               //Расписание рейсов между станциями
                case "/fs":               //Расписание рейсов между станциями

                    return (showScheduleBetStation());


                case "ns":               //Список ближайших станций
                case "/ns":               //Список ближайших станций

                    return (showNearStation());


                case "nc":               //Ближайший город
                case "/nc":               //Ближайший город

                    return (showNeartCity());

                case "ci":               //Информация о перевозчике
                case "/ci":               //Информация о перевозчике

                    return (showInfCarrier());

                default:
                    return ("Not have this command. try write key(-h) or white (-exit), if you want to leave");

            }
        }
    }


    private String showScheduleByStation(MessageStorage messageStorage) {

        StringBuilder Return = new StringBuilder("");

        ParamBuilder param = new ParamBuilder();
        param.setStation(text);
        messageStorage.addMessage(text, chatId);
        try {
            SheduleStation shedule = api.getSheduleStation(param);
            Return.append("тип станции:\t" + shedule.getStation().getStationTypeName());
            Return.append("\n");
            Return.append("название станции:\t" + shedule.getStation().getTitle());
            Return.append("\n");
            Return.append("тип транспорта:\t" + shedule.getStation().getTransportType() + "\n" + "\n");

            for (int i = 0; i < 2/*shedule.schedule.size()*/; ++i) {
                Return.append("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle() + "\n");
                Return.append("даты отъезда:\t" + shedule.getSchedule().get(i).getDays() + "\n");
                Return.append("время отправления:\t" + shedule.getSchedule().get(i).getDays() + "\n");
                Return.append("\n\n");
            }

            return Return.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
