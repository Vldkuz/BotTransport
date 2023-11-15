package Develop.Telegram;

import Develop.Telegram.User.ParamAPI;

public class TelegramFunctions {


    /*public TelegramFunctions(String request) {
        param.setStation(request);
    }*/

    public static String getDataSheduleStation(ParamAPI paramAPI) {
        StringBuilder answer = new StringBuilder("");
        // Здесь нужно добавить просто шаблон, куда будет все подставляться из объекта api
        answer.append("тип станции:\t" + paramAPI.getParamAPIShedule().getStation().getStationTypeName());
        answer.append("\n");
        answer.append("название станции:\t" + paramAPI.getParamAPIShedule().getStation().getTitle());
        answer.append("\n");
        answer.append("тип транспорта:\t" + paramAPI.getParamAPIShedule().getStation().getTransportType() + "\n" + "\n");

        for (int i = 0; i < 3; ++i) {
            answer.append("рейс\t" + paramAPI.getParamAPIShedule().getSchedule().get(i).getThread().getTitle() + "\n");
            answer.append("даты отъезда:\t" + paramAPI.getParamAPIShedule().getSchedule().get(i).getDays() + "\n");
            answer.append("время отправления:\t" + paramAPI.getParamAPIShedule().getSchedule().get(i).getDays() + "\n");
            answer.append("\n\n");
        }

        return answer.toString();

    }
}
