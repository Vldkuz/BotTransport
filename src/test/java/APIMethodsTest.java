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
    private String Black_key = System.getenv("KEY_YANDEX");
    @Test
    void createApiYandexZeroPath(){
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
        });
    }
    @Test
    void getSheduleNoParams() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);});
    }
    @Test
    void getSheduleNoTo() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setTo("s9882586");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);});
    }
    @Test
    void getSheduleNoFrom() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setFrom("s9882586");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);});
    }
    @Test
    void getSheduleStation() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex(Black_key);
            SheduleStation sheduleBetStation = apiYandex.getSheduleStation(paramBuilder);});
    }
    @Test
    void getFollowList() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex(Black_key);
            FollowStations sheduleBetStation = apiYandex.getFollowList(paramBuilder);});
    }
    //    60.625006 - long
    //    56.792159 - lat
    @Test
    void getNearStations() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }
    @Test
    void getNearStationsOnlyLongitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLongitude("60.625006");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }
    @Test
    void getNearStationsOnlyLatitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLatitude("56.792159");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }
    @Test
    void getNearStationsOnlyDistance() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }
    @Test
    void getNearStationsLongitudeAndLatitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLongitude("60.625006");
            paramBuilder.setLatitude("56.792159");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }
    @Test
    void getNearStationsLongitudeAndDistance() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLongitude("60.625006");
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }
    @Test
    void getNearStationsLatitudeAndDistance() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLatitude("56.792159");
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);});
    }
    @Test
    void getNearCity() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);});

        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLongitude("60.625006");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);});

        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLatitude("56.792159");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);});
    }
    @Test
    void getNearCityOnlyLongitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLongitude("60.625006");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);});
    }
    @Test
    void getNearCityOnlyLatitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(Black_key);
            paramBuilder.setLatitude("56.792159");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);});
    }
    @Test
    void getInfoCarrier() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex(Black_key);
            InfoCarrier sheduleBetStation = apiYandex.getInfoCarrier(paramBuilder);});
    }
    /*
    * тесты вне сборки.
    * тесткейсы на каждую ф-ю апи
    * */


//    @Test
//    void getAllowStationsList() {
//        ParamBuilder paramBuilder = new ParamBuilder();
//        assertThrows(ValidationException.class, () -> {
//            APIYandex apiYandex = new APIYandex(Black_key);
//            StationList sheduleBetStation = apiYandex.getAllowStationsList(paramBuilder);});
//    }
}
