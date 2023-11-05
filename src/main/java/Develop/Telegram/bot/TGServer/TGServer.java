package Develop.Telegram.bot.TGServer;

import Develop.API.API;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.ParamBuilder;
import Develop.KeyManager.KeyManager;
import Develop.Telegram.bot.StateObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;

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
            KeyManager KeyAPI = new KeyManager("src/resources/APISheduleKey.json");
            String keyValue = KeyAPI.getKey("Key");
            api = new API(keyValue/*json_api_key*/, "https://api.rasp.yandex.net/v3.0");
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
        if (comand.status.equals("bs")) {
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
                    comand.status = "bs";
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

            for (int i = 0; i < 2/*shedule.schedule.size()*/; ++i) {
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
