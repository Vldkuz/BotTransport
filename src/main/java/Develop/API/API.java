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

public class API implements APIMethods {

  private APIConnector APICon;
  private final ReqBuilder request;

  public API(String APIKey) {
    APICon = new APIConnector(APIKey);
    request = new ReqBuilder("https://api.rasp.yandex.net/v3.0");
  }

  @Override
  public SheduleBetStation getShedule(ParamBuilder params)
      throws IOException, InterruptedException {
    request.setBranch("/search/?");

    if (params.getTo().isEmpty() || params.getFrom().isEmpty()) {
      throw new RuntimeException("Не указан параметр to или from");
    }

    request.addParams(params);

    return getObjMapper(request, SheduleBetStation.class, 1);
  }


  @Override
  public SheduleStation getSheduleStation(ParamBuilder params)
      throws IOException, InterruptedException {
    request.setBranch("/schedule/?");

    if (params.getStation().isEmpty()) {
      throw new RuntimeException("Не указан параметр station");
    }

    request.addParams(params);

    return getObjMapper(request, SheduleStation.class, 1);
  }

  @Override
  public FollowStations getFollowList(ParamBuilder params)
      throws IOException, InterruptedException {
    request.setBranch("/thread/?");

    if (params.getUid().isEmpty()) {
      throw new RuntimeException("Не указан параметр uid");
    }

    request.addParams(params);
    return getObjMapper(request, FollowStations.class, 1);
  }

  @Override
  public NearStations getNearStations(ParamBuilder params)
      throws IOException, InterruptedException {
    request.setBranch("/nearest_stations/?");

    if (params.getLatitude().isEmpty() || params.getLongtitude().isEmpty() || params.getDistance()
        .isEmpty()) {
      throw new RuntimeException("Не указан параметр lat или lng или distance");
    }

    request.addParams(params);

    return getObjMapper(request, NearStations.class,1);
  }

  @Override
  public NearCity getNearCity(ParamBuilder params)
      throws IOException, InterruptedException {
    request.setBranch("/nearest_settlement/?");

    if (params.getLongtitude().isEmpty() || params.getLatitude().isEmpty()) {
      throw new RuntimeException("Не указан параметр lat или lng");
    }

    request.addParams(params);

    return getObjMapper(request, NearCity.class, 1);
  }

  @Override
  public InfoCarrier getInfoCarrier(ParamBuilder params)
      throws IOException, InterruptedException {
    request.setBranch("/carrier/?");

    if (params.getCode().isEmpty()) {
      throw new RuntimeException("Не указан параметр code");
    }

    request.addParams(params);

    return getObjMapper(request, InfoCarrier.class, 1);
  }

  @Override
  public StationList getAllowStationsList() throws IOException, InterruptedException {
    request.setBranch("/stations_list/?");
    return getObjMapper(request, StationList.class, 10);
  }

  private <T> T getObjMapper(ReqBuilder request, Class<T> ClassObj, int timeReq)
      throws IOException, InterruptedException {
    ObjectMapper objMap = new ObjectMapper();
    InputStream StreamAPI = APICon.getInputStream(request.getRequest(), timeReq);
    objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return (T) objMap.readValue(StreamAPI, ClassObj);
  }

}




