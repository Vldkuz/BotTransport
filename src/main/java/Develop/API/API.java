package Develop.API;

import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIObj.StationList.StationList;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.ReqBuilder.ReqBuilder;
import Develop.API.URL.UrlConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class API implements APIMethods {
  private final UrlConnector UrlClient;
  private final ReqBuilder request;

  public API(String APIKey, String APIUrl) {
    UrlClient = new UrlConnector(APIKey);
    request = new ReqBuilder(APIUrl);
  }

  @Override
  public Object getShedule(String to, String from) throws IOException {
    request.setBranch("/search/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("from="+ from);
    params.add("to=" + to);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, SheduleBetStation.class);
  }


  @Override
  public Object getShedule(String station) throws IOException {
    request.setBranch("/schedule/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("station=" + station);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, SheduleStation.class);
  }

  @Override
  public Object getFollowList(String uid) throws IOException {
    request.setBranch("/thread/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("uid=" + uid);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, FollowStations.class);
  }

  @Override
  public Object getNearStations(String latitude, String longitude) throws IOException {
    request.setBranch("/nearest_stations/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("lat=" + latitude);
    params.add("lng=" + longitude);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, NearStations.class);
  }

  @Override
  public Object getNearCity(String latitude, String longitude) throws IOException {
    request.setBranch("/nearest_settlement/?");

    ArrayList<String> params = new ArrayList<String>();
    params.add("lat=" + latitude);
    params.add("lng=" + longitude);

    request.addParams(params);

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, NearCity.class);
  }

  @Override
  public Object getInfoCarrier(String code) throws IOException {
      request.setBranch("/carrier/?");

      ArrayList<String> params = new ArrayList<String>();
      params.add("code=" + code);

      request.addParams(params);

      ObjectMapper objMap = new ObjectMapper();
      InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
      return objMap.readValue(StreamAPI, InfoCarrier.class);
  }

  @Override
  public Object getAllowStationsList() throws IOException {
    request.setBranch("/stations_list/?");

    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = UrlClient.getInputStream(request.getRequest());
    return objMap.readValue(StreamAPI, StationList.class);
  }
}




