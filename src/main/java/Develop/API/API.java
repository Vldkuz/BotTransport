package Develop.API;

import Develop.API.APIConnector.APIConnector;
import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIObj.StationList.StationList;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.ReqBuilder.ReqBuilder;
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
    public SheduleBetStation getShedule(String to, String from, String date, String transportType,
            String limit, String resultTimezone, String withTransfers)
            throws IOException, InterruptedException {
        request.setBranch("/search/?");

        ArrayList<String> params = new ArrayList<String>();

        params.add("from=" + from);
        params.add("to=" + to);

        if (!date.isEmpty()) {
            params.add("date=" + date);
        }
        if (!transportType.isEmpty()) {
            params.add("transport_types=" + transportType);
        }
        if (!limit.isEmpty()) {
            params.add("limit=" + limit);
        }
        if (!resultTimezone.isEmpty()) {
            params.add("result_timezone=" + resultTimezone);
        }
        if (!withTransfers.isEmpty()) {
            params.add("transfers=" + withTransfers);
        }

        request.addParams(params);

        return (SheduleBetStation) getObjMapper(request, SheduleBetStation.class);
    }


    @Override
    public SheduleStation getShedule(String station, String date, String transportType,
            String direction, String event, String resultTimezone)
            throws IOException, InterruptedException {
        request.setBranch("/schedule/?");

        ArrayList<String> params = new ArrayList<String>();
        params.add("station=" + station);

        if (!date.isEmpty()) {
            params.add("date=" + date);
        }
        if (!transportType.isEmpty()) {
            params.add("transport_types=" + transportType);
        }
        if (!direction.isEmpty()) {
            params.add("direction=" + direction);
        }
        if (!event.isEmpty()) {
            params.add("event=" + event);
        }
        if (!resultTimezone.isEmpty()) {
            params.add("result_timezone=" + resultTimezone);
        }

        request.addParams(params);

        return (SheduleStation) getObjMapper(request, SheduleStation.class);
    }

    @Override
    public FollowStations getFollowList(String uid, String from, String to, String date)
            throws IOException, InterruptedException {
        request.setBranch("/thread/?");

        ArrayList<String> params = new ArrayList<String>();
        params.add("uid=" + uid);

        if (!from.isEmpty()) {
            params.add("from=" + from);
        }
        if (!to.isEmpty()) {
            params.add("to=" + to);
        }
        if (!date.isEmpty()) {
            params.add("date=" + date);
        }

        request.addParams(params);

        return (FollowStations) getObjMapper(request, FollowStations.class);
    }

    @Override
    public NearStations getNearStations(String latitude, String longitude, String distance,
            String stationTypes, String transportTypes, String limit)
            throws IOException, InterruptedException {
        request.setBranch("/nearest_stations/?");

        ArrayList<String> params = new ArrayList<String>();
        params.add("lat=" + latitude);
        params.add("lng=" + longitude);
        params.add("distance=" + distance);

        if (!stationTypes.isEmpty()) {
            params.add("station_types=" + stationTypes);
        }
        if (!transportTypes.isEmpty()) {
            params.add("transport_types=" + transportTypes);
        }
        if (!limit.isEmpty()) {
            params.add("limit=" + limit);
        }

        request.addParams(params);

        return (NearStations) getObjMapper(request, NearStations.class);
    }

    @Override
    public NearCity getNearCity(String latitude, String longitude, String distance)
            throws IOException, InterruptedException {
        request.setBranch("/nearest_settlement/?");

        ArrayList<String> params = new ArrayList<String>();
        params.add("lat=" + latitude);
        params.add("lng=" + longitude);

        if (!distance.isEmpty())
            params.add("distance=" + distance);

        request.addParams(params);

        return (NearCity) getObjMapper(request, NearCity.class);
    }

    @Override
    public InfoCarrier getInfoCarrier(String code) throws IOException, InterruptedException {
        request.setBranch("/carrier/?");

        ArrayList<String> params = new ArrayList<String>();
        params.add("code=" + code);

        request.addParams(params);

        return (InfoCarrier) getObjMapper(request, InfoCarrier.class);
    }

    @Override
    public StationList getAllowStationsList() throws IOException, InterruptedException {
        request.setBranch("/stations_list/?");
        return (StationList) getObjMapper(request, StationList.class);
    }

    private Object getObjMapper(ReqBuilder request, Class templateClass) throws IOException, InterruptedException {
        ObjectMapper objMap = new ObjectMapper();
        InputStream StreamAPI = APICon.getInputStream(request.getRequest());
        objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objMap.readValue(StreamAPI, templateClass);
    }

}




