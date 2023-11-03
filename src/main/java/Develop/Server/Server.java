package Develop.Server;

import Develop.API.API;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.ParamBuilder;
import Develop.KeyManager.KeyManager;
import Develop.Main;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.io.OutputStream;

public class Server {

    private API api;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server(InputStream in, OutputStream out) {   // constructor
        // входной вых поток.
        // объект апи
        reader = new BufferedReader(new InputStreamReader(in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
        try {

//            KeyManager keyAPI = new KeyManager("src/resources/APISheduleKey.json");

//            InputStream inputStream = new FileInputStream("src/resources/APISheduleKey.json");

            //InputStream inputStream = KeyManager.class.getResourceAsStream("/APISheduleKey.json");
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Main.class.getResourceAsStream("/APISheduleKey.json");
            KeyManager keyAPI = objectMapper.readValue(inputStream,KeyManager.class);

//            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/APISheduleKey.json");
//            KeyManager keyAPI = new KeyManager(inputStream);



            /*File file = new File("src/resources/APISheduleKey.json");
            InputStream inputStream = new FileInputStream(file);
            KeyManager keyAPI = new KeyManager(inputStream);*/


            String keyValue = keyAPI.getKey();

            api = new API(keyValue/*json_api_key*/, "https://api.rasp.yandex.net/v3.0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showScheduleByStation() {
        writer.println("Schedule by station\n");
        //s9600213
        try {
            String Station = reader.readLine();

            ParamBuilder param = new ParamBuilder();
            param.setStation(Station);

            SheduleStation shedule = api.getSheduleStation(param);
            // Вызываем метод getShedule для получения расписания маршрутов

            writer.println("тип станции:\t" + shedule.getStation().getStationTypeName());
            writer.println("название станции:\t" + shedule.getStation().getTitle());
            writer.println("тип транспорта:\t" + shedule.getStation().getTransportType() + "\n");
            for (int i = 0; i < shedule.getSchedule().size(); ++i) {
                writer.println("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle());
                writer.println("даты отъезда:\t" + shedule.getSchedule().get(i).getDays());
                writer.println("время отправления:\t" + shedule.getSchedule().get(i).getDays());
                writer.println("\n\n");
            }

        } catch (IOException e) {
            // Обрабатываем возможную ошибку ввода-вывода
            e.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }
    public void run() {
        writer.println("//приветствие");
        writer.flush(); // Очистка буфера и запись данных

        String userString = "";
        do {
            try {
                userString = reader.readLine();
            } catch (Exception e) {
                writer.println(e);
            }
            switch (userString) {
                case "-help":       //help
                case "-h":
                case "help":
                case "h":
                    help(writer);
                    break;
                case "fs":               //Расписание рейсов между станциями
                    showScheduleBetStation(writer);
                    break;
                case "bs":               //Расписание рейсов по станции
                    showScheduleByStation(/*writer, reader*/);
                    break;
                case "lot":               //Список станций следования
                    showFollowStations(writer);
                    break;
                case "ns":               //Список ближайших станций
                    showNearStation(writer);
                    break;
                case "nc":               //Ближайший город
                    showNeartCity(writer);
                    break;
                case "ci":               //Информация о перевозчике
                    showInfCarrier(writer);
                    break;
                default:
                    writer.println("Not have this command. try write key(-h) or white (-exit), if you want to leave");
                    writer.flush();
                    break;
            }
            writer.flush();

        } while (!userString.isEmpty());
    }

    private static void help(PrintWriter writer) {
        writer.println("the help code");

    }
    private static void showScheduleBetStation(PrintWriter writer) {
        writer.println("Schedule between stations");

    }

    private static void showFollowStations(PrintWriter writer) {
        writer.println("Follow stations");

    }

    private static void showNearStation(PrintWriter writer) {
        writer.println("List of nearest stations");


    }

    private static void showNeartCity(PrintWriter writer) {
        writer.println("Nearest city");

    }

    private static void showInfCarrier(PrintWriter writer) {
        writer.println("Information about the carrier");

    }
}


