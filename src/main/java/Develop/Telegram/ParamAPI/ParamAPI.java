package Develop.Telegram.ParamAPI;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.API.APIYandex;
import Develop.Telegram.UserHolder.Session;

import java.util.*;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class ParamAPI {

  public static String getStationByLongLant(Location curLoc, Session session)
      throws ValidationException {
    ParamBuilder param = new ParamBuilder();
    param.setLongitude(String.valueOf(curLoc.getLongitude()));
    param.setLatitude(String.valueOf(curLoc.getLatitude()));
    param.setDistance(String.valueOf(5)); // 2 - это радиус в километрах

    try {
      NearStations stations = session.getApiUser().getNearStations(param);
      if (stations.getStations().size() != 0) {
        String station = stations.getStations().get(0).getCode();
        return station;
      }
    } catch (HTTPClientException | ValidationException | ParserException e) {
      throw new ValidationException("Произошла ошибка при обращении по геолокации");
    }

    return "";
  }

  public static void getSheduleStation(String request, Session session, Queue<SendMessage> answer)
      throws ParserException, ValidationException, HTTPClientException {
    session.getInfoHolder().pushStation(request);
    SendMessage sendMessage = new SendMessage();

    ParamBuilder param = new ParamBuilder();
    param.setStation(request);

    SheduleStation shedule = session.getApiUser().getSheduleStation(param);
    StringBuilder firstStr = new StringBuilder();
    // Здесь нужно добавить просто шаблон, куда будет все подставляться из объекта api

    firstStr.append("Тип станции:\t" + shedule.getStation().getStationTypeName());
    firstStr.append("\n");
    firstStr.append("Название станции:\t" + shedule.getStation().getTitle());
    firstStr.append("\n");
    firstStr.append(
        "Тип транспорта:\t" + shedule.getStation().getTransportType() + "\n" + "\n");

    sendMessage.setText(firstStr.toString());
    answer.add(sendMessage);

    for (int i = 0; i < shedule.getSchedule().size(); ++i) {
      sendMessage = new SendMessage();
      StringBuilder ansPart = new StringBuilder();
      ansPart.append("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle() + "\n");
      ansPart.append(
          "номер рейса:\t" + shedule.getSchedule().get(i).getThread().getNumber() + "\n");
      ansPart.append("даты отъезда:\t" + shedule.getSchedule().get(i).getDays() + "\n");
      ansPart.append("время отправления:\t" + shedule.getSchedule().get(i).getDays() + "\n");
      ansPart.append("\n\n");

      sendMessage.setText(ansPart.toString());
      answer.add(sendMessage);
    }
    session.getInfoHolder().pushStation(request);
  }


  public static void getShedule(Session session, Queue<SendMessage> answer)
      throws HTTPClientException, ParserException, ValidationException {
    SendMessage sendMessage = new SendMessage();
    APIYandex api = session.getApiUser();

    ParamBuilder params = new ParamBuilder();
    params.setTo(session.getInfoHolder().getLastDestination());
    params.setFrom(session.getInfoHolder().getLastSource());
//    params.setDate(java.time.LocalDate.now().toString());
//    params.setWithTransfers("true");

    SheduleBetStation shedule = api.getShedule(params);
    // Парсинг объекта shedule Ceмен :)
    // Понял тебя Владислав ☺
    StringBuilder startPart = new StringBuilder("");
    startPart.append(
        shedule.getSearch().getFrom().getTitle() + "(" + shedule.getSearch().getFrom().getCode()
            +
            ")" + "\n\t->\n" + shedule.getSearch().getTo().getTitle() + "("
            + shedule.getSearch().getTo().getCode() + ")");

    sendMessage.setText(String.valueOf(startPart));
    answer.add(sendMessage);

    for (int i = 0; i < shedule.getSegments().size(); ++i) {
      sendMessage = new SendMessage();
      StringBuilder endPart = new StringBuilder();
      endPart.append(
          "Время отправления: " + shedule.getSegments().get(i).getDeparture() + "\n");
      endPart.append("Время прибытия: " + shedule.getSegments().get(i).getArrival() + "\n");
      endPart.append(
          "Ваш маршрут: " + shedule.getSegments().get(i).getThread().getTitle() + "\n");
      endPart.append(
          "Номер маршрута: " + shedule.getSegments().get(i).getThread().getNumber() + "\n");
      endPart.append(
          "Тип транспорта " + shedule.getSegments().get(i).getThread().getTransportType()
              + "\n");
      endPart.append(
          "Тип билета и его кашерность " + shedule.getSegments().get(i).getTicketsInfo()
              .getPlaces().get(0).getName() + "\n" + shedule.getSegments().get(i)
              .getTicketsInfo()
              .getPlaces().get(0).getPrice().getWhole() + "\n");
      sendMessage.setText(endPart.toString());
      answer.add(sendMessage);
    }
  }


  public static void getRecentStations(Session session, Queue<SendMessage> answer) {
    List<String> lastStation = session.getInfoHolder()
        .getLastStation(session.getInfoHolder().getSizeStationHolder());
    if (lastStation.size() == 0) {
      SendMessage message = new SendMessage();
      message.setText("Вы не ввели ни одной станции");
      answer.add(message);
    } else {

      SendMessage message = new SendMessage();
      message.setText("Недавние станции отображены под Вашей строкой");

      ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
      message.setReplyMarkup(keyboardMarkup);

      List<KeyboardRow> keyboard = new ArrayList<>();

      for (int i = 0; i < lastStation.size(); i++) {
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText(lastStation.get(i));

        row.add(button);

        keyboard.add(row);
      }

      keyboardMarkup.setKeyboard(keyboard);

      // Включаем автоматическое скрытие клавиатуры после нажатия кнопки
      keyboardMarkup.setOneTimeKeyboard(true);
      answer.add(message);
    }

  }

  public static boolean validateStation(String station, Session session) {
    ParamBuilder paramSt = new ParamBuilder();
    paramSt.setStation(station);
    try {
      session.getApiUser().getSheduleStation(paramSt);
    } catch (HTTPClientException | ParserException | ValidationException e) {
      return false;
    }
    return true;
  }
}