package Develop.Telegram.User;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;

public class ParamAPI {
    private ParamBuilder param;
    private SheduleStation shedule;

    public ParamAPI(String request, Session session){
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
}
