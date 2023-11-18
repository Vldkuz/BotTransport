package Develop.Telegram.User;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.SheduleStation.Shedule;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;

import java.util.ArrayList;
import java.util.List;

public class ParamAPI {
    private ParamBuilder param;
    private SheduleStation shedule;

    public ParamAPI(String request, Session session) {
        param = new ParamBuilder();
        param.setStation(request);
        try {
            shedule = session.getApiUser().getSheduleStation(param);
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
        for (int i = 0;i< 3;++i){
            title.add(shedule.getSchedule().get(i).getThread().getTitle());
        }
        return title;
    }

    public List<String> getDays() {
        List<String> title = new ArrayList<>();
        for (int i = 0;i< 3;++i){
            title.add(shedule.getSchedule().get(i).getDays());
        }
        return title;
    }
}
//paramAPI.getParamAPIShedule().getSchedule().get(i).getThread().getTitle()