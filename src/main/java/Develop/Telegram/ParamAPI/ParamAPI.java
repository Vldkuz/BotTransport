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

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

    firstStr.append("тип станции:\t" + shedule.getStation().getStationTypeName());
    firstStr.append("\n");
    firstStr.append("название станции:\t" + shedule.getStation().getTitle());
    firstStr.append("\n");
    firstStr.append(
        "тип транспорта:\t" + shedule.getStation().getTransportType() + "\n" + "\n");

    sendMessage.setText(firstStr.toString());
    answer.add(sendMessage);

    for (int i = 0; i < 3/*shedule.getSchedule().size()*/; ++i) {
      SendMessage sendMessage2 = new SendMessage();
      StringBuilder ansPart = new StringBuilder();
      ansPart.append("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle() + "\n");
      ansPart.append(
          "номер рейса:\t" + shedule.getSchedule().get(i).getThread().getNumber() + "\n");
      ansPart.append("даты отъезда:\t" + shedule.getSchedule().get(i).getDays() + "\n");
      ansPart.append("время отправления:\t" + shedule.getSchedule().get(i).getDays() + "\n");
      ansPart.append("\n\n");

      sendMessage2.setText(ansPart.toString());
      answer.add(sendMessage2);
    }
    session.getInfoHolder().pushStation(request);
  }












  public static void getShedule(Session session, Queue<SendMessage> answer) {
    SendMessage sendMessage = new SendMessage();
    APIYandex api = session.getApiUser();

    ParamBuilder params = new ParamBuilder();
    params.setTo(session.getInfoHolder().getLastDestination());
    params.setFrom(session.getInfoHolder().getLastSource());

    try {
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

      for (int i = 0; i < 3/*shedule.getSegments().size()*/; ++i) {
        SendMessage sendMessage2 = new SendMessage();
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
        sendMessage2.setText(endPart.toString());
        answer.add(sendMessage2);
      }


    } catch (HTTPClientException e) {
//      answer.add("Произошла ошибка клиента");
      sendMessage.setText(String.valueOf("Произошла ошибка клиента"));
      answer.add(sendMessage);
    } catch (ParserException e) {
//      answer.add("Произошла ошибка парсера");
      sendMessage.setText(String.valueOf("Произошла ошибка парсера"));
      answer.add(sendMessage);
    } catch (ValidationException e) {
//      answer.add("Произошла ошибка валидации");
      sendMessage.setText(String.valueOf("Произошла ошибка валидации"));
      answer.add(sendMessage);
    }
  }



  public static void getRecentStations(Session session, Queue<SendMessage> answer) {
    List<String> lastStation = session.getInfoHolder().getLastStation(session.getInfoHolder().getSizeStationHolder());
    if (lastStation.size() == 0 ){
      SendMessage message = new SendMessage();
      message.setText("Вы не ввели ни одной станции");
      answer.add(message);
//            curSession.setBlocked(false);
    }
    else {

      SendMessage message = new SendMessage();
      message.setText("Недавние станции отображены под Вашей строкой");

      // Создаем клавиатуру
      ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
      message.setReplyMarkup(keyboardMarkup);

//        Session curSession = sessionHolder.get(String.valueOf(chatId));
      // Создаем список строк клавиатуры


      List<KeyboardRow> keyboard = new ArrayList<>();
//            List<String> lastStation = curSession.getInfoHolder().getLastStation(infoHolder.getSizeStationHolder());

      for (int i = 0; i < lastStation.size(); i++) {
        // Создаем строку клавиатуры
        KeyboardRow row = new KeyboardRow();

        // Создаем кнопку
        KeyboardButton button = new KeyboardButton();
        button.setText(lastStation.get(i));

        // Добавляем кнопку в строку
        row.add(button);

        // Добавляем строку в список
        keyboard.add(row);
      }

      // Привязываем список к клавиатуре
      keyboardMarkup.setKeyboard(keyboard);

      // Включаем автоматическое скрытие клавиатуры после нажатия кнопки
      keyboardMarkup.setOneTimeKeyboard(true);
//            curSession.setBlocked(false);
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









/*
List<String> lastStation = session.getInfoHolder().getLastStation(session.getInfoHolder().getSizeStationHolder());
    if (lastStation.size() == 0 ){
      SendMessage message = new SendMessage();
      message.setText("Вы не ввели ни одной станции");
//            curSession.setBlocked(false);
    }
    else {

      SendMessage message = new SendMessage();
      message.setText("Недавние станции отображены под Вашей строкой");

      // Создаем клавиатуру
      ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
      message.setReplyMarkup(keyboardMarkup);

//        Session curSession = sessionHolder.get(String.valueOf(chatId));
      // Создаем список строк клавиатуры


      List<KeyboardRow> keyboard = new ArrayList<>();
//            List<String> lastStation = curSession.getInfoHolder().getLastStation(infoHolder.getSizeStationHolder());

      for (int i = 0; i < lastStation.size(); i++) {
        // Создаем строку клавиатуры
        KeyboardRow row = new KeyboardRow();

        // Создаем кнопку
        KeyboardButton button = new KeyboardButton();
        button.setText(lastStation.get(i));

        // Добавляем кнопку в строку
        row.add(button);

        // Добавляем строку в список
        keyboard.add(row);
      }

      // Привязываем список к клавиатуре
      keyboardMarkup.setKeyboard(keyboard);

      // Включаем автоматическое скрытие клавиатуры после нажатия кнопки
      keyboardMarkup.setOneTimeKeyboard(true);
//            curSession.setBlocked(false);
    }
 */