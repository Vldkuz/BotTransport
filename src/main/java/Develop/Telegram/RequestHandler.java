package Develop.Telegram;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.Telegram.ParamAPI.ParamAPI;
import Develop.Telegram.UserHolder.Session;
import Develop.Telegram.UserHolder.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.telegram.telegrambots.meta.api.objects.Location;

public class RequestHandler {

  private final Session session;

  public RequestHandler(Session session) {
    this.session = session;
    this.session.setBlocked(true);
  }

  private void handleRequestInteractive(Location curLoc, State state, Queue<String> response)
      throws ValidationException {

    switch (state) {
      case start -> response.add("Для использования геолокации сначала введите команду)");
      case waitSheduleStation -> {
        String station = ParamAPI.getStationByLongLant(curLoc, session);
        try {
          ParamAPI.getSheduleStation(station, session, response);
        } catch (ParserException e) {
          response.add("Произошла ошибка парсера");
        } catch (HTTPClientException e) {
          response.add("Произошла ошибка клиента");
        }
        session.setState(State.start);
      }
      case waitSource -> {
        String station = ParamAPI.getStationByLongLant(curLoc, session);
        session.getInfoHolder().pushStation(station);
        session.getInfoHolder().setLastSource(station);
        response.add("Введите станцию, до которой поедете");
        session.setState(State.waitDestination);
      }

      case waitDestination -> {
        String station = ParamAPI.getStationByLongLant(curLoc, session);
        session.getInfoHolder().setLastDestination(station);
        session.getInfoHolder().pushStation(station);
        session.setState(State.start);

        ParamAPI.getShedule(session, response);
      }

    }


  }

  private void handleRequestText(String request, State state, Queue<String> response) {
    switch (state) {
      case start -> {
        session.setState(getNextState(request));

        switch (session.getState()) {
          case start -> response.add("Что-то с командой");
          case waitSheduleStation -> response.add("Введите код станции:");
          case waitSource -> response.add("Введите код станции, от который поедете:");
          case waitCarrier -> response.add("Введите код перевозчика:");
          case waitDestination -> response.add("Введите код станции, до которой поедете");
          case waitFollowList -> response.add("Введите уникальный идентификатор ветки:");
          case waitNearCity -> response.add("Введите вашу широту и долготу через пробел:");
          case waitNearStations ->
              response.add("Введите вашу ширину, долготу, радиус поиска станции:");
        }
      }

      case waitSheduleStation -> {
        if (getNextState(request) == State.waitSheduleStation) {
          response.add("Введите код станции:");
          return;
        }

        try {
          ParamAPI.getSheduleStation(request, session, response);
          session.setState(State.start);
        }
          catch (HTTPClientException e){
          response.add("Произошла ошибка клиента");
        } catch (ParserException e) {
          response.add("Произошла ошибка парсера");
        } catch (ValidationException e) {
          response.add("Произошла ошибка валидации");
        }
      }

      case waitSource -> {
        if (getNextState(request) == State.waitSource) {
          response.add("Введите код станции, от который поедете:");
          return;
        }

        if (ParamAPI.validateStation(request, session)) {
          session.getInfoHolder().setLastSource(request);
          session.getInfoHolder().pushStation(request);
          session.setState(State.waitDestination);
        } else {
          response.add("Некорректный код станции");
        }
      }

      case waitDestination -> {
        if (getNextState(request) == State.waitDestination) {
          response.add("Введите код станции, до которой поедете:");
          return;
        }

        if (ParamAPI.validateStation(request, session)) {
          session.getInfoHolder().setLastDestination(request);
          session.getInfoHolder().pushStation(request);
          session.setState(State.start);
        } else {
          response.add("Некорректный код станции");
          return;
        }

        ParamAPI.getShedule(session, response);
      }

      case waitCarrier -> {
      }

      case waitNearCity -> {
      }

      case waitFollowList -> {
      }

      case waitNearStations -> {
      }
    }
  }

  public Queue<String> getAnswer(String request, Session session) {
    State state = session.getState(); // Получили текущее состояние пользователя
    Queue<String> answer = new LinkedList<>();

    handleRequestText(request, state, answer);

    session.setBlocked(false);
    return answer;
  }

  public Queue<String> getAnswer(Location curLoc) {
    State state = session.getState();
    Queue<String> answer = new LinkedList<>();

    try {
      handleRequestInteractive(curLoc, state, answer);
    } catch (ValidationException e) {
      answer.add(e.getErrorText());
    }

    session.setBlocked(false);
    return answer;
  }

  private State getNextState(String request) {
    return switch (request) {
      case "bs", "/bs" -> State.waitSheduleStation;
      case "lot" -> State.waitFollowList;
      case "fs", "/fs" -> State.waitSource;
      case "ns", "/ns" -> State.waitNearStations;
      case "nc", "/nc" -> State.waitNearCity;
      case "ci", "/ci" -> State.waitCarrier;
      default -> State.start;
    };
  }
}


