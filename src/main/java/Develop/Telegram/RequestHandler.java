package Develop.Telegram;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.Telegram.ParamAPI.ParamAPI;
import Develop.Telegram.UserHolder.Session;
import Develop.Telegram.UserHolder.State;

import java.util.LinkedList;
import java.util.Queue;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;

public class RequestHandler {

  private final Session session;

  public RequestHandler(Session session) {
    this.session = session;
    this.session.setBlocked(true);
  }

  private void handleRequestInteractive(Location curLoc, State state, Queue<SendMessage> response)
      throws ValidationException {
    SendMessage sendMessage = new SendMessage();
    switch (state) {
      case start -> sendMessage.setText("Для использования геолокации сначала введите команду)");
      case waitSheduleStation -> {
        String station = ParamAPI.getStationByLongLant(curLoc, session);
        try {
          ParamAPI.getSheduleStation(station, session, response);
        } catch (ParserException e) {
          sendMessage.setText("Произошла ошибка парсера");
        } catch (HTTPClientException e) {
          sendMessage.setText("Произошла ошибка клиента");
        }
        session.setState(State.start);
      }
      case waitSource -> {
        String station = ParamAPI.getStationByLongLant(curLoc, session);
        session.getInfoHolder().pushStation(station);
        session.getInfoHolder().setLastSource(station);
        sendMessage.setText("Введите станцию, до которой поедете");
        session.setState(State.waitDestination);
      }

      case waitDestination -> {
        String station = ParamAPI.getStationByLongLant(curLoc, session);
        session.getInfoHolder().setLastDestination(station);
        session.getInfoHolder().pushStation(station);
        session.setState(State.start);

        try {
          ParamAPI.getShedule(session, response);
        } catch (HTTPClientException e){
          sendMessage.setText("Произошла ошибка клиента");
        } catch (ParserException e) {
          sendMessage.setText("Произошла ошибка парсера");
        } catch (ValidationException e) {
          sendMessage.setText("Произошла ошибка валидации");
        }
      }
    }
    response.add(sendMessage);


  }

  private void handleRequestText(String request, State state, Queue<SendMessage> response) {
    SendMessage sendMessage =  new SendMessage();
    switch (state) {

      case start -> {
        session.setState(getNextState(request));

        switch (session.getState()) {
          case start -> sendMessage.setText("Что-то с командой");
          case waitSheduleStation -> sendMessage.setText("Введите код станции:");
          case waitSource -> sendMessage.setText("Введите код станции, от который поедете:");
          case waitCarrier -> sendMessage.setText("Введите код перевозчика:");
          case waitDestination -> sendMessage.setText("Введите код станции, до которой поедете");
          case waitFollowList -> sendMessage.setText("Введите уникальный идентификатор ветки:");
          case waitNearCity -> sendMessage.setText("Введите вашу широту и долготу через пробел:");
          case waitNearStations ->
              sendMessage.setText("Введите вашу ширину, долготу, радиус поиска станции:");
          case waitRecentStations ->{
          ParamAPI.getRecentStations(session,response);
          session.setState(State.start);}
        }
      }

      case waitSheduleStation -> {
        if (getNextState(request) == State.waitSheduleStation) {
          sendMessage.setText("Введите код станции:");
          response.add(sendMessage);
          return;
        }

        try {
          ParamAPI.getSheduleStation(request, session, response);
          session.setState(State.start);
        }
          catch (HTTPClientException e){
          sendMessage.setText("Произошла ошибка клиента");
        } catch (ParserException e) {
          sendMessage.setText("Произошла ошибка парсера");
        } catch (ValidationException e) {
          sendMessage.setText("Произошла ошибка валидации");
        }
      }

      case waitSource -> {
        if (getNextState(request) == State.waitSource) {
          sendMessage.setText("Введите код станции, от который поедете:");
          response.add(sendMessage);
          return;
        }

        if (ParamAPI.validateStation(request, session)) {
          session.getInfoHolder().setLastSource(request);
          session.getInfoHolder().pushStation(request);
          session.setState(State.waitDestination);
        } else {
          sendMessage.setText("Некорректный код станции");
        }
      }

      case waitDestination -> {
        if (getNextState(request) == State.waitDestination) {
          sendMessage.setText("Введите код станции, до которой поедете:");
          response.add(sendMessage);
          return;
        }

        if (ParamAPI.validateStation(request, session)) {
          session.getInfoHolder().setLastDestination(request);
          session.getInfoHolder().pushStation(request);
          session.setState(State.start);
        } else {
          sendMessage.setText("Некорректный код станции");
          response.add(sendMessage);
          return;
        }

        try {
          ParamAPI.getShedule(session, response);
        } catch (HTTPClientException e){
          sendMessage.setText("Произошла ошибка клиента");
        } catch (ParserException e) {
          sendMessage.setText("Произошла ошибка парсера");
        } catch (ValidationException e) {
          sendMessage.setText("Произошла ошибка валидации");
        }
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
    response.add(sendMessage);
  }

  public Queue<SendMessage> getAnswer(String request, Session session) {
    State state = session.getState(); // Получили текущее состояние пользователя
    Queue<SendMessage> answer = new LinkedList<>();

    handleRequestText(request, state, answer);

    session.setBlocked(false);
    return answer;
  }

  public Queue<SendMessage> getAnswer(Location curLoc) {
    State state = session.getState();
    Queue<SendMessage> answer = new LinkedList<>();
    SendMessage sendMessage = new SendMessage();
    try {
      handleRequestInteractive(curLoc, state, answer);
    } catch (ValidationException e) {
      sendMessage.setText(e.getErrorText());
      answer.add(sendMessage);
    }

    session.setBlocked(false);
    return answer;
  }

  private State getNextState(String request) {
    return switch (request) {
      case "bs", "/bs" -> State.waitSheduleStation;
      case "rs", "/rs" -> State.waitRecentStations;
      case "lot" -> State.waitFollowList;
      case "fs", "/fs" -> State.waitSource;
      case "ns", "/ns" -> State.waitNearStations;
      case "nc", "/nc" -> State.waitNearCity;
      case "ci", "/ci" -> State.waitCarrier;
      default -> State.start;
    };
  }
}


