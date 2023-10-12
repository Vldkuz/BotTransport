package Develop.API;

import Develop.APIObj.StationList;
import Develop.ReqBuilder.ReqBuilder;
import Develop.URL.UrlConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class API implements APIMethods {

    private final UrlConnector UrlClient;
    private final ReqBuilder request;

    public API(String APIKey,
            String APIUrl) { // Для работы с API требуется инициализация ключа и url.
        UrlClient = new UrlConnector(APIKey);
        request = new ReqBuilder(APIUrl);
    }

    @Override
    public Object getShedule(String to, String from) {

        return null;
    }


    @Override
    public Object getShedule(String station) {

        return null;
    }

    @Override
    public Object getFollowList(String uid) {
        return null;
    }

    @Override
    public Object getNearStations(String latitude, String longitude) {

        return null;
    }

    @Override
    public Object getNearCity(String latitude, String longitude) {
        return null;
    }

    @Override
    public Object getInfoCarrier(String code) {
        return null;
    }

    @Override
    public Object getAllowStationsList() throws IOException {
        request.setBranch("/stations_list/?");

        ObjectMapper objMap = new ObjectMapper();
        InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
        return  objMap.readValue(StreamAPI, StationList.class);
    }
}




