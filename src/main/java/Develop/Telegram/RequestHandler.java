package Develop.Telegram;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.NearStations.Station;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.Telegram.ParamAPI.ParamAPI;
import Develop.Telegram.UserHolder.Session;
import Develop.Telegram.UserHolder.State;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedList;
import java.util.Queue;

public class RequestHandler {

    private final Session session;

    public RequestHandler(Session session) {
        this.session = session;
    }

    public Queue<String> getAnswer(String request, Update update, Double latitude, Double longitude) {
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
                if (session.getState() == State.waitDataShedule) {
                    answer.add("Введите код станции:");
                }
            }

            case waitDataShedule -> {
                State next = getNextState(request);
                session.setState(next);
                if (session.getState() == State.waitDataShedule)
                    answer.add("Введите код станции:");
                else {
                    ParamBuilder param = new ParamBuilder();
                    param.setLatitude(String.valueOf(latitude));
                    param.setLongitude(String.valueOf(longitude));
                    param.setDistance(String.valueOf(1));
//                    param.setStation(request);
                    try {
                        NearStations station = session.getApiUser().getNearStations(param);
                        Station station1 = (station.getStations().get(0));
                        String s =station1.getCode();
                        param.setStation(s);
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

                        for (int i = 0; i < 3/*shedule.getSchedule().size()*/; ++i) {
                            StringBuilder ansPart = new StringBuilder();
                            ansPart.append("рейс\t" + shedule.getSchedule().get(i).getThread().getTitle() + "\n");
                            ansPart.append("даты отъезда:\t" + shedule.getSchedule().get(i).getDays() + "\n");
                            ansPart.append("время отправления:\t" + shedule.getSchedule().get(i).getDays() + "\n");
                            ansPart.append("\n\n");

                            answer.add(ansPart.toString());
                        }

                    } catch (ParserException e) {
                        answer.add("Произошла ошибка парсера");
                    } catch (HTTPClientException e) {
                        answer.add("Произошла ошибка клиента");
                    } catch (ValidationException e) {
                        answer.add("Произошла ошибка валидации");
                    }
                }
            }


            case waitDataSheduleStation -> {
                State next = getNextState(request);
                session.setState(next);
                if (session.getState() == State.waitDataSheduleStation)
                    answer.add("Введите код станции:");
                else {
                    answer.addAll(ParamAPI.getDataSheduleStation(request, session));
                }
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
            case "bs", "/bs" -> State.waitDataSheduleStation;
            case "lot" -> State.waitDataFollowList;
            case "fs", "/fs" -> State.waitDataShedule;
            case "ns" -> State.waitDataNearStations;
            case "nc" -> State.waitDataNearCity;
            case "ci" -> State.waitDataInfoCarrier;
//            case "rs", "/rs" -> State.waitRecentStations;
            default -> State.start;
        };
    }
}
