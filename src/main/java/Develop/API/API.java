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
  public SheduleBetStation getShedule(String to, String from)
          throws IOException, InterruptedException {
    request.setBranch("/search/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("from="+ from);
    params.add("to=" + to);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = APICon.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, SheduleBetStation.class);
  }


  @Override
  public SheduleStation getShedule(String station) throws IOException, InterruptedException {
    request.setBranch("/schedule/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("station=" + station);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = APICon.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, SheduleStation.class);
  }

  @Override
  public FollowStations getFollowList(String uid) throws IOException, InterruptedException {
    request.setBranch("/thread/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("uid=" + uid);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = APICon.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, FollowStations.class);
  }

  @Override
  public NearStations getNearStations(String latitude, String longitude)
          throws IOException, InterruptedException {
    request.setBranch("/nearest_stations/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("lat=" + latitude);
    params.add("lng=" + longitude);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = APICon.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, NearStations.class);
  }

  @Override
  public NearCity getNearCity(String latitude, String longitude)
          throws IOException, InterruptedException {
    request.setBranch("/nearest_settlement/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("lat=" + latitude);
    params.add("lng=" + longitude);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = APICon.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, NearCity.class);
  }

  @Override
  public InfoCarrier getInfoCarrier(String code) throws IOException, InterruptedException {
      request.setBranch("/carrier/?");

      ArrayList<String> params = new ArrayList<String>();
      params.add("code=" + code);

      request.addParams(params);

      ObjectMapper objMap = new ObjectMapper();
      InputStream StreamAPI = APICon.getInputStream(request.getRequest());
      return objMap.readValue(StreamAPI, InfoCarrier.class);
  }

  @Override
  public StationList getAllowStationsList() throws IOException, InterruptedException {
    request.setBranch("/stations_list/?");

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = APICon.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, StationList.class);
  }

}




