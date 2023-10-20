import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Develop.API.API;
import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.KeyManager.KeyManager;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAPI {

    @Test
    public void testValidationAPI() {
        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            API emptyKey = new API("", "url");
            API emptyUrl = new API("key", "");
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    public void TestAPI() {

        try {
            KeyManager key = new KeyManager("src/resources/APISheduleKey.json");
            API api = new API(key.getKey("Key"), "https://api.rasp.yandex.net/v3.0");
            SheduleStation shedule = api.getShedule("s9600213", "", "", "", "", "");
            SheduleBetStation sheduleBetStation = api.getShedule("c146", "c213", "", "", "", "",
                    "");
            FollowStations folStat = api.getFollowList("SU-1524_231029_c26_12", "", "", "");
            NearStations nearStations = api.getNearStations("50.4516962252837", "40.1392928134917",
                    "50", "", "", "");
            NearCity city = api.getNearCity("54.106677", "39.601726", "");
            InfoCarrier carrier = api.getInfoCarrier("680");
        }
        catch (IOException e) {
            Assertions.assertNull(e);
        } catch (InterruptedException e) {
            Assertions.assertNull(e);
        }
    }
}
