package Develop.Telegram.ParamAPI;

import java.util.LinkedList;
import java.util.Queue;

public class TelegramFunctions {


    /*public TelegramFunctions(String request) {
        param.setStation(request);
    }*/

    public static Queue<String> getDataSheduleStation(ParamAPI paramAPI/*, String stationTypeName, String title, String transportType, List<String> thread, List<String> days*//*Queue<String> answer*/) {
        Queue<String> answer = new LinkedList<>();
        StringBuilder firstStr = new StringBuilder("");
        // Здесь нужно добавить просто шаблон, куда будет все подставляться из объекта api
        firstStr.append("тип станции:\t" +paramAPI.getStationTypeName());
        firstStr.append("\n");
        firstStr.append("название станции:\t" + paramAPI.getTitle());
        firstStr.append("\n");
        firstStr.append("тип транспорта:\t" + paramAPI.getTransportType() + "\n" + "\n");
        answer.add(firstStr.toString());

        for (int i = 0; i < paramAPI.getSheduleSize(); ++i) {
            StringBuilder ansPart = new StringBuilder();
            ansPart.append("рейс\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getThread().getTitle()*/paramAPI.getThreadTitle().get(i) + "\n");
            ansPart.append("даты отъезда:\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getDays()*/paramAPI.getDays().get(i) + "\n");
            ansPart.append("время отправления:\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getDays()*/paramAPI.getDays().get(i) + "\n");
//            ansPart.append("\n\n");
            answer.add(firstStr.toString());
        }

        return answer;

    }

    public static String getDataShedule(ParamAPI paramAPI) {
        StringBuilder answer = new StringBuilder("");
        // Здесь нужно добавить просто шаблон, куда будет все подставляться из объекта api
        answer.append("тип станции:\t" +paramAPI.getStationTypeName());
        answer.append("\n");
        answer.append("название станции:\t" + paramAPI.getTitle());
        answer.append("\n");
        answer.append("тип транспорта:\t" + paramAPI.getTransportType() + "\n" + "\n");

        for (int i = 0; i < paramAPI.getSheduleSize(); ++i) {
            answer.append("рейс\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getThread().getTitle()*/paramAPI.getThreadTitle().get(i) + "\n");
            answer.append("даты отъезда:\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getDays()*/paramAPI.getDays().get(i) + "\n");
            answer.append("время отправления:\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getDays()*/paramAPI.getDays().get(i) + "\n");
            answer.append("\n\n");
        }

        return answer.toString();
    }
}

