import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Develop.API.API;
import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.ParamBuilder;
import Develop.KeyManager.KeyManager;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import Develop.Main;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestAP {

    @Test
    public void testValidationAPI() {
        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            API emptyKey = new API("", "url");
            API emptyUrl = new API("key", "");
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestValidationMethods(){
        try
        {
            KeyManager key = new KeyManager();
            key.setKey("/APISheduleKey.json");
            API api = new API(key.getKey(), "https://api.rasp.yandex.net/v3.0");

            testSheduleStation(api,"");
            testSheduleBetStation(api, "","");
            testFollowStations(api,"");
            testInfoCarrier(api,"");
            testNearStations(api,"","","");
            testnearcity(api,"", "");


        } catch (IOException e) {
            Assertions.assertNull(e);
        } catch (InterruptedException e) {
            Assertions.assertNull(e);
        } catch (RuntimeException e) {
            Assertions.assertNotNull(e);
        }
    }


    public void testSheduleStation(API api, String station)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.setStation(station);
        TestCase.setDate(LocalDate.now().toString());
        SheduleStation shedule = api.getSheduleStation(TestCase);
    }

    public void testSheduleBetStation(API api, String to, String from)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.setTo(to);
        TestCase.setFrom(from);
        TestCase.setDate(LocalDate.now().toString());

        SheduleBetStation shedule =  api.getShedule(TestCase);
    }

    public void testFollowStations(API api, String uid) throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.setUid(uid);
        TestCase.setDate(LocalDate.now().toString());

        FollowStations folStat = api.getFollowList(TestCase);
    }

    public void testNearStations(API api, String lat, String lng, String dist)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.setLatitude(lat);
        TestCase.setLongtitude(lng);
        TestCase.setDistance(dist);
        TestCase.setDate(LocalDate.now().toString());

        NearStations nearStat = api.getNearStations(TestCase);
    }

    public void testInfoCarrier(API api, String code)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.setCode(code);
        TestCase.setDate(LocalDate.now().toString());
        InfoCarrier InfoCarrier = api.getInfoCarrier(TestCase);
    }

    public void testnearcity(API api, String lat, String lng)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.setLatitude(lat);
        TestCase.setLongtitude(lng);
        TestCase.setDate(LocalDate.now().toString());

        NearCity nearStat = api.getNearCity(TestCase);
    }


    @Test
    public void TestAPI() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Main.class.getResourceAsStream("/APISheduleKey.json");
            KeyManager keyAPI = objectMapper.readValue(inputStream, KeyManager.class);

            String key = keyAPI.getKey();
            API api = new API(key,"https://api.rasp.yandex.net/v3.0");

            testSheduleStation(api,"s9600213");
            testSheduleBetStation(api, "c146","c213");
            testInfoCarrier(api,"680");
            testNearStations(api,"50.4516962252837","40.1392928134917","50");
            testnearcity(api,"54.106677", "39.601726");

            // Здесь можно подтягивать эти данные из файла и прогонять на большем количестве тесткейсов.
        }
        catch (IOException e) {
            Assertions.assertNull(e);
        } catch (InterruptedException e) {
            Assertions.assertNull(e);
        }
    }
}
