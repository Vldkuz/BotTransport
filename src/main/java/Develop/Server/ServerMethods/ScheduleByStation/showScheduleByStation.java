package Develop.Server.ServerMethods.ScheduleByStation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class showScheduleByStation {

    public void go(PrintWriter writer, BufferedReader reader) throws IOException {
        writer.println("Schedule by station");
        try {
            /*String api_key = "YOUR_API_KEY";
            String api_url = "https://api.example.com/";*/
            String to = reader.readLine();

            //API api =new API(api_key, api_url);
            // SheduleStation schedule =  api.getShedule(to);
//             String schedule = (String) api.getShedule(to);
            // Вызываем метод getShedule для получения расписания маршрутов

            writer.println("Well there is your Schedule: ");// + schedule);
        } catch (IOException e) {
            // Обрабатываем возможную ошибку ввода-вывода
            e.printStackTrace();
        }
        //разборка schedule объекта
    }
}