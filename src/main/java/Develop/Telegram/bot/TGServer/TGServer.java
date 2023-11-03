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
        param.station = text;
        try {
            SheduleStation shedule = api.getSheduleStation(param);
            Return.append("тип станции:\t" + shedule.station.station_type_name);
            Return.append("\n");
            Return.append("название станции:\t" + shedule.station.title);
            Return.append("\n");
            Return.append("тип транспорта:\t" + shedule.station.transport_type + "\n" + "\n");

            for (int i = 0; i < 2; ++i) {
                Return.append("рейс\t" + shedule.schedule.get(i).thread.title + "\n");
                Return.append("даты отъезда:\t" + shedule.schedule.get(i).days + "\n");
                Return.append("время отправления:\t" + shedule.schedule.get(i).days + "\n");
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
            return (showScheduleByStation());
        } else {
            switch (text) {
                case "h": //help
                    return help();

                case "bs":               //Расписание рейсов по станции
                    comand.setStatus("bs");
                    return ("Write code of city, please ");

                case "lot":               //Список станций следования
                    return (showFollowStations());


                case "fs":               //Расписание рейсов между станциями

                    return (showScheduleBetStation());


                case "ns":               //Список ближайших станций

                    return (showNearStation());


                case "nc":               //Ближайший город

                    return (showNeartCity());

                case "ci":               //Информация о перевозчике

                    return (showInfCarrier());

                default:
                    return ("Not have this command. try write key(-h) or white (-exit), if you want to leave");

            }
        }
    }
}
