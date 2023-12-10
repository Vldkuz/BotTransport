

import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIServices.ParamBuilder;
import Develop.API.APIYandex;
import Develop.Telegram.RequestHandler;
import Develop.Telegram.SessionHolder.SessionHolder;
import Develop.Telegram.UserHolder.InfoHolder;
import Develop.Telegram.UserHolder.Session;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

}

@Testcontainers
class DatabaseTest {
    private static String keyYandex = System.getenv("KEY_YANDEX");
    private static String dbName = "Session";
    private static int dbPort = 5432;
    private static String dbUrl = "localhost";
    private static String dbUser = "postgres";
    private static String dbPasswd = "superpassword";
    private static String chatId= "12342324";

    private static SessionHolder sessionHolder = new SessionHolder(keyYandex,dbName,dbUrl,String.valueOf(dbPort),dbUser,dbPasswd);

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
        "postgres:16")
        .withDatabaseName(dbName)
        .withUsername(dbUser)
        .withPassword(dbPasswd)
        .withExposedPorts(dbPort);
    @Test
    public void testDatabase()
    {
        Session templateSession = new Session(keyYandex);
        InfoHolder template  = templateSession.getInfoHolder();
        template.pushStation("s9600213");
        template.pushStation("s9600212");
        template.setLastSource("s9600213");
        template.setLastDestination("s9600212");


        Session curSession = sessionHolder.get(chatId);
        RequestHandler requestHandler = new RequestHandler(curSession);


        // Вот здесь тестовые методы дергай только со станциями

        curSession = sessionHolder.get(chatId);

        assertEquals(template.getLastSource(), curSession.getInfoHolder().getLastSource());
        assertEquals(template.getLastDestination(), curSession.getInfoHolder().getLastDestination());
        assertEquals(templateSession.getState(), curSession.getState());

        String[] templateStationList = template.getStationList().toArray(String[]::new);
        String[] curStationList = template.getStationList().toArray(String[]::new);

        assertArrayEquals(templateStationList, curStationList);
        // Здесь я должен проверить, состояние сессии в бд
    }

}



