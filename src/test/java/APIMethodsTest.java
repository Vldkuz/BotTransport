import Develop.API.APIExceptions.ValidationException;
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

import static org.junit.jupiter.api.Assertions.*;

class APIMethodsTest {
    private String blackKey = System.getenv("KEY_YANDEX");
    private boolean boomTestsFlag = Boolean.parseBoolean(System.getenv("BT_FLAG"));
    private boolean dataBaseFlag = Boolean.parseBoolean(System.getenv("DB_FLAG"));
    

    @Test
    void createApiYandexZeroPath() {
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
        });
        
    }

    @Test
    void getSheduleNoParams() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleNoTo() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setTo("s9882586");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleNoFrom() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setFrom("s9882586");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleStation() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            
            SheduleStation sheduleBetStation = apiYandex.getSheduleStation(paramBuilder);
        });
    }

    @Test
    void getFollowList() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            
            FollowStations sheduleBetStation = apiYandex.getFollowList(paramBuilder);
        });
    }
    @Test
    void getNearStations() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsOnlyLongitude() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLongitude("60.625006");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsOnlyLatitude() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLatitude("56.792159");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsOnlyDistance() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsLongitudeAndLatitude() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLongitude("60.625006");
            paramBuilder.setLatitude("56.792159");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsLongitudeAndDistance() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLongitude("60.625006");
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsLatitudeAndDistance() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLatitude("56.792159");
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearCity() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });

        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLongitude("60.625006");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });

        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLatitude("56.792159");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });
    }

    @Test
    void getNearCityOnlyLongitude() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLongitude("60.625006");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });
    }

    @Test
    void getNearCityOnlyLatitude() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ValidationException.class, () -> {

            ParamBuilder paramBuilder = new ParamBuilder();
            
            paramBuilder.setLatitude("56.792159");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });
    }

    @Test
    void getInfoCarrier() {
        APIYandex apiYandex;
        try {
            apiYandex = new APIYandex(blackKey);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            
            InfoCarrier sheduleBetStation = apiYandex.getInfoCarrier(paramBuilder);
        });
    }

    @Test
    void doBoomTests() {
        if (boomTestsFlag) {
            assertDoesNotThrow(() -> BoomTests.getSheduleStation());
            assertDoesNotThrow(() -> BoomTests.getShedule());
        }
    }

    @Test
    void doDBTests() {
        if (dataBaseFlag) {
            DatabaseTest db = new DatabaseTest();
            db.runContainer();
            assertDoesNotThrow(() -> db.lastDestination());
            assertDoesNotThrow(() -> db.lastSource());
            assertDoesNotThrow(() -> db.Name());
            assertDoesNotThrow(() -> db.pushStation());

            db.stopContainer();
        }
    }
}