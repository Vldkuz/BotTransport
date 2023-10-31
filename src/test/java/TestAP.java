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
            KeyManager key = new KeyManager("src/resources/APISheduleKey.json");
            API api = new API(key.getKey("Key"), "https://api.rasp.yandex.net/v3.0");

            TestSheduleStation(api,"");
            TestSheduleBetStation(api, "","");
            TestFollowStations(api,"");
            TestInfoCarrier(api,"");
            TestNearStations(api,"","","");
            TestNearCity(api,"", "");


        } catch (IOException e) {
            Assertions.assertNull(e);
        } catch (InterruptedException e) {
            Assertions.assertNull(e);
        } catch (RuntimeException e) {
            Assertions.assertNotNull(e);
        }
    }


    public void TestSheduleStation(API api, String station)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.station = station;
        SheduleStation shedule = api.getSheduleStation(TestCase);
    }

    public void TestSheduleBetStation (API api, String to, String from)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.to = to;
        TestCase.from = from;

        SheduleBetStation shedule =  api.getShedule(TestCase);
    }

    public void TestFollowStations (API api, String uid) throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.uid = uid;

        FollowStations folStat = api.getFollowList(TestCase);
    }

    public void TestNearStations (API api, String lat, String lng, String dist)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.latitude = lat;
        TestCase.longtitude = lng;
        TestCase.distance = dist;

        NearStations nearStat = api.getNearStations(TestCase);
    }

    public void TestInfoCarrier (API api, String code)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.code = code;
        InfoCarrier InfoCarrier = api.getInfoCarrier(TestCase);
    }

    public void TestNearCity (API api, String lat, String lng)
            throws IOException, InterruptedException {
        ParamBuilder TestCase = new ParamBuilder();
        TestCase.latitude = lat;
        TestCase.longtitude = lng;

        NearCity nearStat = api.getNearCity(TestCase);
    }


    @Test
    public void TestAPI() {

        try {

            KeyManager key = new KeyManager("src/resources/APISheduleKey.json");
            API api = new API(key.getKey("Key"), "https://api.rasp.yandex.net/v3.0");

            TestSheduleStation(api,"s9600213");
            TestSheduleBetStation(api, "c146","c213");
            TestFollowStations(api,"SU-1524_231029_c26_12");
            TestInfoCarrier(api,"680");
            TestNearStations(api,"50.4516962252837","40.1392928134917","50");
            TestNearCity(api,"54.106677", "39.601726");

            // Здесь можно подтягивать эти данные из файла и прогонять на большем количестве тесткейсов.
        }
        catch (IOException e) {
            Assertions.assertNull(e);
        } catch (InterruptedException e) {
            Assertions.assertNull(e);
        }
    }
}
