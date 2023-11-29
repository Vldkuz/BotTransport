package Develop.Telegram;

import Develop.Telegram.ParamAPI.ParamAPI;
import Develop.Telegram.UserHolder.Session;
import Develop.Telegram.UserHolder.State;

import java.util.LinkedList;
import java.util.Queue;

public class RequestHandler {

    private final Session session;
    private boolean isInteractive = false;

    public RequestHandler(Session session) {
        this.session = session;
    }

    private void handleRequestInteractive(String request, State state, Queue<String> response) {
    }

    private void handleRequestText(String request, State state, Queue<String> response) {
        switch (state) {
            case start -> {
                session.setState(getNextState(request));

                switch (state) {
                    case start -> response.add("Что-то с командой");
                    case waitSheduleStation -> response.add("Введите код станции:");
                    case waitSource -> response.add("Введите код станции, от который поедете:");
                    case waitCarrier -> response.add("Введите код перевозчика:");
                    case waitDestination -> response.add("Введите код станции, до которой поедете");
                    case waitFollowList -> response.add("Введите уникальный идентификатор ветки:");
                    case waitNearCity -> response.add("Введите вашу широту и долготу через пробел:");
                    case waitNearStations -> response.add("Введите вашу ширину, долготу, радиус поиска станции:");
                }
            }

            case waitSheduleStation -> {
                if (getNextState(request) == State.waitSheduleStation) {
                    response.add("Введите код станции:");
                    return;
                }
                ParamAPI.getSheduleStation(request, session, response);
            }

            case waitSource -> {
                if (getNextState(request) == State.waitSource) {
                    response.add("Введите код станции, от который поедете:");
                    return;
                }


                if (ParamAPI.validateStation(request, session)) {
                    session.getInfoHolder().setLastSource(request);
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
                    session.setState(State.start);
                } else {
                    response.add("Некорректный код станции");
                    return;
                }

                ParamAPI.getShedule(session, response);
            }


        }
    }

    public Queue<String> getAnswer(String request, Session session) {
        State state = session.getState(); // Получили текущее состояние пользователя
        Queue<String> answer = new LinkedList<>();

        if (isInteractive) {
            handleRequestInteractive(request, state, answer);
        } else {
            handleRequestText(request, state, answer);
        }

        session.setBlocked(false);
        return answer;
    }

    private State getNextState(String request) {
        return switch (request) {
            case "bs", "/bs" -> State.waitSheduleStation;
            case "lot" -> State.waitFollowList;
            case "fs", "/fs" -> State.waitSource;
            case "ns" -> State.waitNearStations;
            case "nc" -> State.waitNearCity;
            case "ci" -> State.waitCarrier;
            default -> State.start;
        };
    }
}


