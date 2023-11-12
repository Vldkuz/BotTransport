package Develop.Telegram;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.Telegram.UserHolder.Session;
import Develop.Telegram.UserHolder.State;

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

      }
      case waitDataShedule -> {

      }
      case waitDataSheduleStation -> {
        session.setState(getNextState(request));
        if (session.getState() == State.waitDataSheduleStation)
          return "Введите код станции:";

        StringBuilder answer = new StringBuilder("");
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

        } catch (ParserException e) {
          return "Произошла ошибка парсера";
        } catch (HTTPClientException e) {
          return "Произошла ошибка клиента";
        } catch (ValidationException e) {
          return "Произошла ошибка валидации";
        }

        session.getInfoHolder().pushStation(request);
        return answer.toString();
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
      case "bs":
        return State.waitDataSheduleStation;
      case "h", "help":
        return State.help;
      case "lot":
        return State.waitDataFollowList;
      case "fs":
        return State.waitDataShedule;
      case "ns":
        return State.waitDataNearStations;
      case "nc":
        return State.waitDataNearCity;
      case "ci":
        return State.waitDataInfoCarrier;
      default:
        return State.start;
    }
  }
}
