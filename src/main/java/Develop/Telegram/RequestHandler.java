package Develop.Telegram;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.Telegram.UserHolder.Session;
import Develop.Telegram.UserHolder.State;
import java.util.LinkedList;
import java.util.Queue;

public class RequestHandler {

  private final Session session;

  public RequestHandler(Session session) {
    this.session = session;
  }

  public Queue<String> getAnswer(String request) {
    State state = session.getState(); // Получили текущее состояние пользователя
    Queue<String> answer = new LinkedList<>();

    switch (state) {
      case start -> {
        session.setState(getNextState(request));

        if (session.getState() == State.start) {
          answer.add("Что-то с командой");
        }

        if (session.getState() == State.waitDataSheduleStation) {
          answer.add("Введите код станции:");
        }
      }

      case waitDataShedule -> {

      }

      case waitDataSheduleStation -> {
        State next = getNextState(request);
        session.setState(next);

        if (session.getState() == State.waitDataSheduleStation)
          answer.add("Введите код станции:");

        ParamBuilder param = new ParamBuilder();
        param.setStation(request);
        try {
          SheduleStation shedule = session.getApiUser().getSheduleStation(param);

          StringBuilder firstStr = new StringBuilder("");
          // Здесь нужно добавить просто шаблон, куда будет все подставляться из объекта api

          firstStr.append("тип станции:\t" + shedule.getStation().getStationTypeName());
          firstStr.append("\n");
          firstStr.append("название станции:\t" + shedule.getStation().getTitle());
          firstStr.append("\n");
          firstStr.append(
              "тип транспорта:\t" + shedule.getStation().getTransportType() + "\n" + "\n");

          answer.add(firstStr.toString());

          for (int i = 0; i < shedule.getSchedule().size(); ++i) {
            StringBuilder ansPart = new StringBuilder();
            ansPart.append("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle() + "\n");
            ansPart.append("даты отъезда:\t" + shedule.getSchedule().get(i).getDays() + "\n");
            ansPart.append("время отправления:\t" + shedule.getSchedule().get(i).getDays() + "\n");
            ansPart.append("\n\n");

            answer.add(ansPart.toString());
          }

        }

          catch (ParserException e) {
          answer.add("Произошла ошибка парсера");
        } catch (HTTPClientException e) {
          answer.add("Произошла ошибка клиента");
        } catch (ValidationException e) {
          answer.add("Произошла ошибка валидации");
        }

        session.getInfoHolder().pushStation(request);
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

    session.setBlocked(false);
    return answer;
  }

  private State getNextState(String request) {
    return switch (request) {
      case "bs" -> State.waitDataSheduleStation;
      case "lot" -> State.waitDataFollowList;
      case "fs" -> State.waitDataShedule;
      case "ns" -> State.waitDataNearStations;
      case "nc" -> State.waitDataNearCity;
      case "ci" -> State.waitDataInfoCarrier;
      default -> State.start;
    };
  }
}
