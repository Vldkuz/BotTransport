package Develop.Telegram.ParamAPI;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.Telegram.UserHolder.Session;

import java.util.ArrayList;
import java.util.List;

public class ParamAPI {
    private ParamBuilder param;
    private SheduleStation shedule;
    private SheduleBetStation shedule2;

    public ParamAPI(String request, Session session, String nameOfFunction) {
        param = new ParamBuilder();
        try {
            switch (nameOfFunction) {
                case "waitDataSheduleStation":
                    param.setStation(request);
                    SheduleStation shedule = session.getApiUser().getSheduleStation(param);
                case "waitDataShedule":

                    SheduleBetStation shedule2 = session.getApiUser().getShedule(param);
            }

        } catch (HTTPClientException e) {
            throw new RuntimeException(e);
        } catch (ParserException e) {
            throw new RuntimeException(e);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public SheduleStation getParamAPIShedule() {
        return shedule;
    }

    public String getStationTypeName() {
        return shedule.getStation().getStationTypeName();
    }

    public String getTitle() {
        return shedule.getStation().getTitle();
    }

    public String getTransportType() {
        return shedule.getStation().getTransportType();
    }

    public List<String> getThreadTitle() {
        List<String> title = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            title.add(shedule.getSchedule().get(i).getThread().getTitle());
        }
        return title;
    }

    public List<String> getDays() {
        List<String> title = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            title.add(shedule.getSchedule().get(i).getDays());
        }
        return title;
    }

    public int getSheduleSize() {
        return shedule.getSchedule().size();
    }

//    public
}
//paramAPI.getParamAPIShedule().getSchedule().get(i).getThread().getTitle()