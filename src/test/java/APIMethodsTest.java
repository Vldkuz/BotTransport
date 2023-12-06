

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
    private String BlackKey = System.getenv("KEY_YANDEX");
    private boolean boomTestsFlag = Boolean.parseBoolean(System.getenv("BT_FLAG"));;
    private boolean dataBaseFlag = Boolean.parseBoolean(System.getenv("DB_FLAG"));;

//    public void main(String[] args) {
//        if (args[1].equals("-bt")) {
//            boomTestsFlag = true;
//        }
//        if (args[2].equals("-db")) {
//            dataBaseFlag = true;
//        }
//    }

    @Test
    void createApiYandexZeroPath() {
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex("");
        });
    }

    @Test
    void getSheduleNoParams() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleNoTo() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setTo("s9882586");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleNoFrom() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setFrom("s9882586");
            SheduleBetStation sheduleBetStation = apiYandex.getShedule(paramBuilder);
        });
    }

    @Test
    void getSheduleStation() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex(BlackKey);
            SheduleStation sheduleBetStation = apiYandex.getSheduleStation(paramBuilder);
        });
    }

    @Test
    void getFollowList() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex(BlackKey);
            FollowStations sheduleBetStation = apiYandex.getFollowList(paramBuilder);
        });
    }

    //    60.625006 - long
    //    56.792159 - lat
    @Test
    void getNearStations() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsOnlyLongitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLongitude("60.625006");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsOnlyLatitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLatitude("56.792159");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsOnlyDistance() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsLongitudeAndLatitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLongitude("60.625006");
            paramBuilder.setLatitude("56.792159");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsLongitudeAndDistance() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLongitude("60.625006");
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearStationsLatitudeAndDistance() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLatitude("56.792159");
            paramBuilder.setDistance("5");
            NearStations sheduleBetStation = apiYandex.getNearStations(paramBuilder);
        });
    }

    @Test
    void getNearCity() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });

        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLongitude("60.625006");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });

        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLatitude("56.792159");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });
    }

    @Test
    void getNearCityOnlyLongitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLongitude("60.625006");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });
    }

    @Test
    void getNearCityOnlyLatitude() {
        assertThrows(ValidationException.class, () -> {
            ParamBuilder paramBuilder = new ParamBuilder();
            APIYandex apiYandex = new APIYandex(BlackKey);
            paramBuilder.setLatitude("56.792159");
            NearCity sheduleBetStation = apiYandex.getNearCity(paramBuilder);
        });
    }

    @Test
    void getInfoCarrier() {
        ParamBuilder paramBuilder = new ParamBuilder();
        assertThrows(ValidationException.class, () -> {
            APIYandex apiYandex = new APIYandex(BlackKey);
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
            assertDoesNotThrow(() -> DatabaseTest.lastDestination());
            assertDoesNotThrow(() -> DatabaseTest.lastSource());
            assertDoesNotThrow(() -> DatabaseTest.Name());
            assertDoesNotThrow(() -> DatabaseTest.pushStation());
        }
    }
    /*
     * тесты вне сборки.
     * тесткейсы на каждую ф-ю апи
     * */


//    @Test
//    void getAllowStationsList() {
//        ParamBuilder paramBuilder = new ParamBuilder();
//        assertThrows(ValidationException.class, () -> {
//            APIYandex apiYandex = new APIYandex(BlackKey);
//            StationList sheduleBetStation = apiYandex.getAllowStationsList(paramBuilder);});
//    }
}





























//@Testcontainers
//class DatabaseTest {
//    private static String keyYandex = System.getenv("KEY_YANDEX");
//    private static String dbName = "Session";
//    private static int dbPort = 5432;
//    private static String dbUrl = "localhost";
//    private static String dbUser = "postgres";
//    private static String dbPasswd = "superpassword";
//    private static String chatId= "12342324";
//
//    private static SessionHolder sessionHolder = new SessionHolder(keyYandex,dbName,dbUrl,String.valueOf(dbPort),dbUser,dbPasswd);
//
//    @Container
//    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
//        "postgres:16")
//        .withDatabaseName(dbName)
//        .withUsername(dbUser)
//        .withPassword(dbPasswd)
//        .withExposedPorts(dbPort);
//    @Test
//    public void testDatabase()
//    {
//        Session templateSession = new Session(keyYandex);
//        InfoHolder template  = templateSession.getInfoHolder();
//        template.pushStation("s9600213");
//        template.pushStation("s9600212");
//        template.setLastSource("s9600213");
//        template.setLastDestination("s9600212");
//
//
//        Session curSession = sessionHolder.get(chatId);
//        RequestHandler requestHandler = new RequestHandler(curSession);
//
//
//        // Вот здесь тестовые методы дергай только со станциями
//
//        curSession = sessionHolder.get(chatId);
//
//        assertEquals(template.getLastSource(), curSession.getInfoHolder().getLastSource());
//        assertEquals(template.getLastDestination(), curSession.getInfoHolder().getLastDestination());
//        assertEquals(templateSession.getState(), curSession.getState());
//
//        String[] templateStationList = template.getStationList().toArray(String[]::new);
//        String[] curStationList = template.getStationList().toArray(String[]::new);
//
//        assertArrayEquals(templateStationList, curStationList);
//        // Здесь я должен проверить, состояние сессии в бд
//    }
//
//}



