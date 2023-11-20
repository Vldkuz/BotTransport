package Develop.Telegram;

import Develop.Telegram.User.ParamAPI;
import Develop.Telegram.User.Session;
import Develop.Telegram.User.State;

public class RequestHandler {

    private Session session;

    public RequestHandler(Session session) {
        this.session = session;
    }

    public String getAnswer(String request) {
        State state = session.getState(); // Получили текущее состояние пользователя

        switch (state) {
            case start -> {
                session.setState(getNextState(request));

                if (session.getState() == State.start) {
                    return "Что-то с командой";
                }

                if (session.getState() == State.waitDataSheduleStation) {
                    return "Введите код станции:";
                }

                if (session.getState() == State.help) {
                    session.setState(State.start);
                    return "help code";
                }

            }
            case waitDataShedule -> {

            }
            case waitDataSheduleStation -> {
                session.setState(getNextState(request));
                if (session.getState() == State.waitDataSheduleStation)
                    return "Введите код станции:";


//                try {
//                    ParamBuilder param = new ParamBuilder();
//                    param.setStation(request);
//                    SheduleStation shedule = session.getApiUser().getSheduleStation(param);

                ParamAPI paramAPI = new ParamAPI(request, session);
                return TelegramFunctions.getDataSheduleStation(paramAPI/*, paramAPI.getStationTypeName(),paramAPI.getTitle(),paramAPI.getTransportType(),paramAPI.getThreadTitle(),paramAPI.getDays()*/);
                    /*TelegramFunctions telegramFunctions = new TelegramFunctions();
                    return telegramFunctions.getDataSheduleStation(shedule);*/
//                } catch (ParserException e) {
//
//                } catch (ValidationException e) {
//
//                } catch (HTTPClientException e) {
//                }


        /*StringBuilder answer = new StringBuilder("");
        ParamBuilder param = new ParamBuilder();
        param.setStation(request);

        try {
          SheduleStation shedule = session.getApiUser().getSheduleStation(param);

          // Здесь нужно добавить просто шаблон, куда будет все подставляться из объекта api

          answer.append("тип станции:\t" + shedule.getStation().getStationTypeName());
          answer.append("\n");
          answer.append("название станции:\t" + shedule.getStation().getTitle());
          answer.append("\n");
          answer.append(
              "тип транспорта:\t" + shedule.getStation().getTransportType() + "\n" + "\n");

          for (int i = 0; i < 3; ++i) {
            answer.append("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle() + "\n");
            answer.append("даты отъезда:\t" + shedule.getSchedule().get(i).getDays() + "\n");
            answer.append("время отправления:\t" + shedule.getSchedule().get(i).getDays() + "\n");
            answer.append("\n\n");
          }

          return answer.toString();

        } catch (ParserException e) {
          // Добавить обработку ошибок парсера
        } catch (HTTPClientException e) {
          // Добавить обработку ошибок клиента
        } catch (ValidationException e) {
          // Добавить обработку ошибок валидации
        }*/
            }
            case waitDataFollowList -> {

            }
            case waitDataNearStations -> {

            }
            case waitDataNearCity -> {

            }
            case waitDataInfoCarrier -> {

            }
        }

        return "";
    }

    private State getNextState(String request) {
        switch (request) {
            case "bs", "/bs":
                return State.waitDataSheduleStation;
            case "h", "help", "/h", "/help":
                return State.help;
            case "lot", "/lot":
                return State.waitDataFollowList;
            case "fs", "/fs":
                return State.waitDataShedule;
            case "ns", "/ns":
                return State.waitDataNearStations;
            case "nc", "/nc":
                return State.waitDataNearCity;
            case "ci", "/ci":
                return State.waitDataInfoCarrier;
            default:
                return State.start;
        }
    }
}
