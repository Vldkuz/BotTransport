package Develop.API;

import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIObj.StationList.StationList;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class API implements APIMethods {

    private final APIConnector APICon;
    private final ReqBuilder request;

    public API(String APIKey, String APIUrl) {
        APICon = new APIConnector(APIKey);
        request = new ReqBuilder(APIUrl);
    }

    @Override
    public SheduleBetStation getShedule(ParamBuilder params)
            throws IOException, InterruptedException {
        request.setBranch("/search/?");

        if (params.to.isEmpty() || params.from.isEmpty()) {
            throw new RuntimeException("Не указан параметр to или from");
        }

        request.addParams(params);

        return getObjMapper(request, SheduleBetStation.class);
    }


    @Override
    public SheduleStation getSheduleStation(ParamBuilder params)
            throws IOException, InterruptedException {
        request.setBranch("/schedule/?");

        if (params.station.isEmpty()) {
            throw new RuntimeException("Не указан параметр station");
        }

        request.addParams(params);

        return (SheduleStation) getObjMapper(request, SheduleStation.class);
    }

    @Override
    public FollowStations getFollowList(ParamBuilder params)
            throws IOException, InterruptedException {
        request.setBranch("/thread/?");

        if (params.uid.isEmpty()) {
            throw new RuntimeException("Не указан параметр uid");
        }

        request.addParams(params);
        return (FollowStations) getObjMapper(request, FollowStations.class);
    }

    @Override
    public NearStations getNearStations(ParamBuilder params)
            throws IOException, InterruptedException {
        request.setBranch("/nearest_stations/?");

        if (params.latitude.isEmpty() || params.longtitude.isEmpty() || params.distance.isEmpty()) {
            throw new RuntimeException("Не указан параметр lat или lng или distance");
        }

        request.addParams(params);

        return (NearStations) getObjMapper(request, NearStations.class);
    }

    @Override
    public NearCity getNearCity(ParamBuilder params)
            throws IOException, InterruptedException {
        request.setBranch("/nearest_settlement/?");

        if (params.longtitude.isEmpty() || params.latitude.isEmpty()) {
            throw new RuntimeException("Не указан параметр lat или lng");
        }

        request.addParams(params);

        return (NearCity) getObjMapper(request, NearCity.class);
    }

    @Override
    public InfoCarrier getInfoCarrier(ParamBuilder params)
            throws IOException, InterruptedException {
        request.setBranch("/carrier/?");

        if (params.code.isEmpty()) {
            throw new RuntimeException("Не указан параметр code");
        }

        request.addParams(params);

        return (InfoCarrier) getObjMapper(request, InfoCarrier.class);
    }

    @Override
    public StationList getAllowStationsList() throws IOException, InterruptedException {
        request.setBranch("/stations_list/?");
        return (StationList) getObjMapper(request, StationList.class);
    }

    private <T> T getObjMapper(ReqBuilder request, Class<T> templateClass)
            throws IOException, InterruptedException {
        ObjectMapper objMap = new ObjectMapper();
        InputStream StreamAPI = APICon.getInputStream(request.getRequest());
        objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objMap.readValue(StreamAPI, templateClass);
    }

}




