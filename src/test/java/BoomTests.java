import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.API.APIYandex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class BoomTests {
    private static final String BlackKey = System.getenv("KEY_YANDEX");

    @Test
    static void getSheduleStation() throws HTTPClientException, ParserException, ValidationException {
        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setStation("s9882574");
        APIYandex apiYandex = new APIYandex(BlackKey);
        SheduleStation sheduleBetStation = apiYandex.getSheduleStation(paramBuilder);
    }

    @Test
    static void getShedule() throws HTTPClientException, ParserException, ValidationException {
        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setTo("s9882586");
        paramBuilder.setFrom("s9882574");
        APIYandex apiYandex = new APIYandex(BlackKey);
        SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
    }
}
