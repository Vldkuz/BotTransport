package Develop.Telegram;

import Develop.Telegram.User.ParamAPI;

import java.util.List;

public class TelegramFunctions {


    /*public TelegramFunctions(String request) {
        param.setStation(request);
    }*/

    public static String getDataSheduleStation(ParamAPI paramAPI/*, String stationTypeName, String title, String transportType, List<String> thread, List<String> days*/) {
        StringBuilder answer = new StringBuilder("");
        // Здесь нужно добавить просто шаблон, куда будет все подставляться из объекта api
        answer.append("тип станции:\t" +paramAPI.getStationTypeName());
        answer.append("\n");
        answer.append("название станции:\t" + paramAPI.getTitle());
        answer.append("\n");
        answer.append("тип транспорта:\t" + paramAPI.getTransportType() + "\n" + "\n");

        for (int i = 0; i < 3; ++i) {
            answer.append("рейс\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getThread().getTitle()*/paramAPI.getThreadTitle().get(i) + "\n");
            answer.append("даты отъезда:\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getDays()*/paramAPI.getDays().get(i) + "\n");
            answer.append("время отправления:\t" + /*paramAPI.getParamAPIShedule().getSchedule().get(i).getDays()*/paramAPI.getDays().get(i) + "\n");
            answer.append("\n\n");
        }

        return answer.toString();

    }
}
