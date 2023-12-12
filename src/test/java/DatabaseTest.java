import Develop.Telegram.SessionHolder.SessionHolder;
import Develop.Telegram.UserHolder.InfoHolder;
import Develop.Telegram.UserHolder.Session;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class DatabaseTest {
    private static String keyYandex = System.getenv("KEY_YANDEX");
    private static String dbName = "Session";
    private static int dbPort = 5432;
    private static String dbUrl = "127.0.0.1";
    private static String dbUser = "postgres";
    private static String dbPasswd = "superpassword";
    private static String chatId= "12342324";

    private static SessionHolder sessionHolder = new SessionHolder(keyYandex,dbName,dbUrl,String.valueOf(dbPort),dbUser,dbPasswd);

    @Container
    private PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName(dbName)
            .withUsername(dbUser)
            .withPassword(dbPasswd)
            .withExposedPorts(dbPort)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(dbPort), new ExposedPort(dbPort)))));

    public void runContainer() {postgreSQLContainer.start();}
    public void stopContainer() {
        sessionHolder.switchOFFUpload();
        postgreSQLContainer.stop();
    }

    @Test
    public static void pushStation()
    {
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
//        assertEquals(template.getLastDestination(), curSession.getInfoHolder().getLastDфestination());
//        assertEquals(templateSession.getState(), curSession.getState());
//
//        String[] templateStationList = template.getStationList().toArray(String[]::new);
//        String[] curStationList = template.getStationList().toArray(String[]::new);
//
//        assertArrayEquals(templateStationList, curStationList);
//        // Здесь я должен проверить, состояние сессии в бд


        Session templateSession = new Session(keyYandex);
        InfoHolder template  = templateSession.getInfoHolder();
        template.pushStation("s9600213");

        Session curSession = sessionHolder.get(chatId);
        curSession.getInfoHolder().pushStation("s9600213");
        curSession.setBlocked(false);
        // Вот здесь тестовые методы дергай только со станциями

        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {}

        curSession = sessionHolder.get(chatId);

        String[] templateStationList = template.getStationList().toArray(String[]::new);
        String[] curStationList = curSession.getInfoHolder().getStationList().toArray(String[]::new);

        if (templateStationList.equals(curStationList))
            throw new IllegalStateException();

        // Здесь я должен проверить, состояние сессии в бд
    }


    @Test
    public static void lastDestination() throws IllegalStateException {
        Session templateSession = new Session(keyYandex);
        InfoHolder template  = templateSession.getInfoHolder();
        template.setLastDestination("s9600213");

        Session curSession = sessionHolder.get(chatId);
        curSession.getInfoHolder().setLastDestination("s9600213");
        curSession.setBlocked(false);

        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {}

        curSession = sessionHolder.get(chatId);

        String templateStationList = template.getLastDestination();
        String curStationList = curSession.getInfoHolder().getLastDestination();

        if (!templateStationList.equals(curStationList))
            throw new IllegalStateException();
    }

    @Test
    public static void lastSource() throws IllegalStateException {
        Session templateSession = new Session(keyYandex);
        InfoHolder template  = templateSession.getInfoHolder();
        template.setLastSource("s9600213");

        Session curSession = sessionHolder.get(chatId);
        curSession.getInfoHolder().setLastSource("s9600213");
        curSession.setBlocked(false);

        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {}

        curSession = sessionHolder.get(chatId);

        String templateStationList = template.getLastSource();
        String curStationList = curSession.getInfoHolder().getLastSource();

        if (!templateStationList.equals(curStationList))
            throw new IllegalStateException();
    }


    @Test
    public static void Name()
    {
        Session templateSession = new Session(keyYandex);
        InfoHolder template  = templateSession.getInfoHolder();
        template.setName("Панэ Пык_МЫКола"); //который свой домен в ru засуваф фыфыф

        Session curSession = sessionHolder.get(chatId);
        curSession.getInfoHolder().setName("Панэ Пык_МЫКола");
        curSession.setBlocked(false);

        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {}

        curSession = sessionHolder.get(chatId);

        String templateStationList = template.getName();
        String curStationList = curSession.getInfoHolder().getName();

        if (!templateStationList.equals(curStationList))
            throw new IllegalStateException();
    }


}
