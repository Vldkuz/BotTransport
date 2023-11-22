

import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.API.APIYandex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
class APIMethodsTest {
    @Test
    void createApiYandex(){
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
        });
    }
    @Test
    void getShedule() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleStation() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
            SheduleStation sheduleBetStation = apiYandex.getSheduleStation(paramBuilder);});
    }

    @Test
    void getFollowList() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
            FollowStations sheduleBetStation = apiYandex.getFollowList(paramBuilder);});
    }

    @Test
    void getNearStations() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }

    @Test
    void getNearCity() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);});
    }

    @Test
    void getInfoCarrier() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
            InfoCarrier sheduleBetStation = apiYandex.getInfoCarrier(paramBuilder);});
    }

//    @Test
//    void getAllowStationsList() {
//        ParamBuilder paramBuilder = new ParamBuilder();
//        assertThrows(ValidationException.class, () -> {
//            APIYandex apiYandex = new APIYandex("");
//            StationList sheduleBetStation = apiYandex.getAllowStationsList(paramBuilder);});
//    }
}
