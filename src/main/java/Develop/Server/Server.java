package Develop.Server;

import Develop.API.API;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.io.OutputStream;

public class Server {

    private API api;
    private BufferedReader reader;// = new BufferedReader(new InputStreamReader(in));
    private PrintWriter writer;// = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));

    public Server(InputStream in, OutputStream out) {   // constructor
        // входной вых поток.
        // объект апи
        reader = new BufferedReader(new InputStreamReader(in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));

        try {
            // Путь к JSON файлу
            String filePath = "src/resources/APISheduleKey.json";

            // Создание объекта ObjectMapper из библиотеки Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Чтение файла и создание JsonNode объекта
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            // Получение значения "Key" из JsonNode объекта
            String keyValue = jsonNode.get("Key").asText();
            api = new API(keyValue/*json_api_key*/, "https://api.rasp.yandex.net/v3.0");

            //System.out.println(keyValue); // Выводит "2e07c9e6-4de0-486d-accd-95e725fd87bc"
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String api_key = "YOUR_API_KEY";
        // String api_url = "https://api.example.com/";
        // api = new API(keyValue/*json_api_key*/, "https://api.rasp.yandex.net/v3.0");

    }

    private void showScheduleByStation(/*PrintWriter writer, BufferedReader reader*/) /*throws IOException*/ {
        writer.println("Schedule by station");

        try {
            String Station = reader.readLine();
            SheduleStation shedule = (SheduleStation) api.getShedule(Station);
//            shedule.station
            // Вызываем метод getShedule для получения расписания маршрутов
            writer.println(shedule.date);

            //String to = reader.readLine();

            // writer.println("Well there is your Schedule: " + schedule);
        } catch (IOException e) {
            // Обрабатываем возможную ошибку ввода-вывода
            e.printStackTrace();
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }

    }

    public void run() {
//        try (reader = new BufferedReader(new InputStreamReader(reader)); PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(writer)))) {

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
                    /*case "-exit":       //exit
                        exit(writer);
                        break;*/
//                case "-F":          //file
//                    fileWork(writer);
//                    break;
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
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void help(PrintWriter writer) {
        writer.println("the help code");

    }

  /*  private static void exit(PrintWriter writer) {
        writer.println("the exit. God buy)");
        writer.flush();
        System.exit(0);
    }*/

    /*private static void fileWork() {
        try {
            PrintStream OutFile = new PrintStream("1.txt");
            OutFile.println("text");
            System.out.println("good write");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }*/


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

/*
private static void fs() {
    System.out.println("Flight schedule between stations");
}

private static void bs() {
    System.out.println("Flight schedule by station");
}

private static void los() {
    System.out.println("List of train stations");
}

private static void ns() {
    System.out.println("List of nearest stations");
}

private static void nc() {
    System.out.println("Nearest city");
}

private static void ci() {
    System.out.println("Information about the carrier");
}
 */
