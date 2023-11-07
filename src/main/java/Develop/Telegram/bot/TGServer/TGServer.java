package Develop.Telegram.bot.TGServer;

import Develop.API.API;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.ParamBuilder;
import Develop.KeyManager.KeyManager;
import Develop.Main;
import Develop.Telegram.bot.StateObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TGServer {

    private final String text;
    private final API api;
    public TGServer(String text) {

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

    public String help() {
        return "The help code";
    }

    public String showScheduleBetStation() {
        return "Schedule between stations";
    }

    public String showFollowStations() {
        return "Follow stations";
    }

    public String showNearStation() {
        return "List of nearest stations";
    }

    public String showNeartCity() {
        return "Nearest city";
    }

    public String showInfCarrier() {
        return "Information about the carrier";
    }

    public String showScheduleByStation() {

        StringBuilder Return = new StringBuilder("");
        ParamBuilder param = new ParamBuilder();
        //param.date = "2023-11-04";
        param.setStation(text);
        try {
            SheduleStation shedule = api.getSheduleStation(param);
            Return.append("тип станции:\t" + shedule.getStation().getStationTypeName());
            Return.append("\n");
            Return.append("название станции:\t" + shedule.getStation().getTitle());
            Return.append("\n");
            Return.append("тип транспорта:\t" + shedule.getStation().getTransportType() + "\n" + "\n");

            for (int i = 0; i < 2; ++i) {
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

    public String run(String text, StateObject comand) {
        if (comand.getStatus().equals("bs")) {
            return showScheduleByStation();
        } else {
            switch (text) {
                case "h": //help
                case "/h": //help
                    return help();

                case "bs":               //Расписание рейсов по станции
                case "/bs":               //Расписание рейсов по станции
                    comand.setStatus("bs");
                    return ("Write code of city, please ");

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
}





















/*
package Develop.Telegram.bot.TGServer;

import Develop.API.API;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.ParamBuilder;
import Develop.KeyManager.KeyManager;
import Develop.Main;
import Develop.Telegram.bot.StateObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.io.InputStream;

public class TGServer {

    private final API api;

    private final String chatId;
    private final String text;
    private final SendMessage sendMessage;

    public TGServer(String chatId, String text, SendMessage sendMessage) {
        this.chatId = chatId;
        this.text = text;
        this.sendMessage = sendMessage;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Main.class.getResourceAsStream("/APISheduleKey.json");
            KeyManager keyAPI = objectMapper.readValue(inputStream,KeyManager.class);
            String keyValue = keyAPI.getKey();
            api = new API(keyValue*/
/*json_api_key*//*
, "https://api.rasp.yandex.net/v3.0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String help() {
        return "The help code";
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

    public void run(StateObject comand) {
        sendMessage.setChatId(chatId);
        if (comand.getStatus().equals("bs")) {
                sendMessage.setText(showScheduleByStation());
        }
        else {
            switch (text) {
                case "h": //help

                    sendMessage.setText(help());
                    break;
                case "fs":               //Расписание рейсов между станциями

                    sendMessage.setText(showScheduleBetStation());

                    break;
                case "bs":               //Расписание рейсов по станции
//                text = update.getMessage().getText();
//                sendMessage.setText(text);
//                SendMessage response = new SendMessage().setChatId(update.getMessage().getChatId()).setText(responseText);
//                sendMessage.setText(response.getText());
                    //text = update.getMessage().getText();
//                System.out.println(sendMessage.getText());
                    comand.setStatus("bs");
                    sendMessage.setText("Write code of city, please ");
                    break;
                case "lot":               //Список станций следования

                    sendMessage.setText(showFollowStations());

                    break;
                case "ns":               //Список ближайших станций

                    sendMessage.setText(showNearStation());

                    break;
                case "nc":               //Ближайший город

                    sendMessage.setText(showNeartCity());
                    break;
                case "ci":               //Информация о перевозчике

                    sendMessage.setText(showInfCarrier());
                    break;
                default:
                    sendMessage.setText("Not have this command. try write key(-h) or white (-exit), if you want to leave");
                    break;
            }
        }
    }



    public String showScheduleByStation() {

        StringBuilder Return = new StringBuilder("");
        ParamBuilder param = new ParamBuilder();
        param.setStation(text);
        try {
            SheduleStation shedule = api.getSheduleStation(param);
            Return.append("тип станции:\t" + shedule.getStation().getStationTypeName());
            Return.append("\n");
            Return.append("название станции:\t" + shedule.getStation().getTitle());
            Return.append("\n");
            Return.append("тип транспорта:\t" + shedule.getStation().getTransportType()+"\n"+"\n");

            for (int i = 0; i < 2*/
/*shedule.schedule.size()*//*
; ++i) {
                Return.append("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle()+"\n");
                Return.append("даты отъезда:\t" + shedule.getSchedule().get(i).getDays() +"\n");
                Return.append("время отправления:\t" + shedule.getSchedule().get(i).getDays()+"\n");
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
*/
