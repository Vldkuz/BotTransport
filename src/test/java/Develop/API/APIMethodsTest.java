package Develop.API;

import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIServices.ParamBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class APIMethodsTest {


    @Test
    void getShedule() {
        /*APIYandex apiYandex;
        {
            try {
                apiYandex = new APIYandex("s9600212");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }*/
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("s9600212");
        });
        ParamBuilder paramBuilder = new ParamBuilder();
//        SheduleBetStation sheduleBetStation; /*= apiYandex.getShedule(paramBuilder);*/
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("s9600212");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleStation() {
    }

    @Test
    void getFollowList() {
    }

    @Test
    void getNearStations() {
    }

    @Test
    void getNearCity() {
    }

    @Test
    void getInfoCarrier() {
    }

    @Test
    void getAllowStationsList() {
    }
}