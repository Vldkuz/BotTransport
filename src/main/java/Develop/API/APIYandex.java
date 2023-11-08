package Develop.API;

import Develop.API.APIExceptions.FieldHolder;
import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIObj.StationList.StationList;
import Develop.API.APIServices.APIConnector;
import Develop.API.APIServices.JSONParser;
import Develop.API.APIServices.ParamBuilder;
import Develop.API.APIServices.ReqBuilder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class APIYandex implements APIMethods {

  private APIConnector APICon;
  private final ReqBuilder request;
  private JSONParser jsonParser;

  public APIYandex(String APIKey) throws ValidationException {
    APICon = new APIConnector(APIKey);
    request = new ReqBuilder("https://api.rasp.yandex.net/v3.0");
  }

  @Override
  public SheduleBetStation getShedule(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException {
    request.setBranch("/search/?");

    if (params.getTo().isEmpty() || params.getFrom().isEmpty()) {

      FieldHolder holder = new FieldHolder("to", params.getTo());
      FieldHolder holder1 = new FieldHolder("from", params.getFrom());
      List<FieldHolder> holders = new ArrayList<>();
      holders.add(holder);
      holders.add(holder1);

      throw new ValidationException("Не указан параметр to или from", holders);
    }

    request.addParams(params);
    jsonParser = new JSONParser();
    Duration time = Duration.ofMinutes(2);

    return jsonParser.parseIntoObj(SheduleBetStation.class, request.getRequest(), APICon, time);
  }


  @Override
  public SheduleStation getSheduleStation(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException {
    request.setBranch("/schedule/?");

    if (params.getStation().isEmpty()) {

      FieldHolder holder = new FieldHolder("station", params.getStation());
      List<FieldHolder> holders = new ArrayList<>();
      holders.add(holder);

      throw new ValidationException("Не указан параметр station", holders);
    }

    request.addParams(params);

    jsonParser = new JSONParser();
    Duration time = Duration.ofMinutes(2);

    return jsonParser.parseIntoObj(SheduleStation.class, request.getRequest(), APICon, time);
  }

  @Override
  public FollowStations getFollowList(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException {
    request.setBranch("/thread/?");

    if (params.getUid().isEmpty()) {
      FieldHolder holder = new FieldHolder("uid", params.getUid());
      List<FieldHolder> holders = new ArrayList<>();
      holders.add(holder);

      throw new ValidationException("Не указан параметр uid", holders);
    }

    request.addParams(params);

    jsonParser = new JSONParser();
    Duration time = Duration.ofMinutes(2);

    return jsonParser.parseIntoObj(FollowStations.class, request.getRequest(), APICon, time);
  }

  @Override
  public NearStations getNearStations(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException {
    request.setBranch("/nearest_stations/?");

    if (params.getLatitude().isEmpty() || params.getLongitude().isEmpty() || params.getDistance()
        .isEmpty()) {
      FieldHolder holder = new FieldHolder("lat", params.getLatitude());
      FieldHolder holder1 = new FieldHolder("lng", params.getLongitude());
      FieldHolder holder2 = new FieldHolder("distance", params.getDistance());

      List<FieldHolder> holders = new ArrayList<>();

      holders.add(holder);
      holders.add(holder1);
      holders.add(holder2);

      throw new ValidationException("Не указан параметры lat, lng, distance", holders);
    }

    request.addParams(params);

    jsonParser = new JSONParser();
    Duration time = Duration.ofMinutes(2);

    return jsonParser.parseIntoObj(NearStations.class, request.getRequest(), APICon, time);
  }

  @Override
  public NearCity getNearCity(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException {
    request.setBranch("/nearest_settlement/?");

    if (params.getLongitude().isEmpty() || params.getLatitude().isEmpty()) {

      FieldHolder holder = new FieldHolder("lat", params.getLatitude());
      FieldHolder holder1 = new FieldHolder("lng", params.getLongitude());

      List<FieldHolder> holders = new ArrayList<>();

      holders.add(holder);
      holders.add(holder1);

      throw new ValidationException("Не указан параметры lat, lng", holders);
    }

    request.addParams(params);

    jsonParser = new JSONParser();
    Duration time = Duration.ofMinutes(2);

    return jsonParser.parseIntoObj(NearCity.class, request.getRequest(), APICon, time);
  }

  @Override
  public InfoCarrier getInfoCarrier(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException {
    request.setBranch("/carrier/?");

    if (params.getCode().isEmpty()) {
      FieldHolder holder = new FieldHolder("code", params.getCode());
      List<FieldHolder> holders = new ArrayList<>();

      holders.add(holder);

      throw new ValidationException("Не указан параметр code", holders);
    }

    request.addParams(params);

    jsonParser = new JSONParser();
    Duration time = Duration.ofMinutes(2);

    return jsonParser.parseIntoObj(InfoCarrier.class, request.getRequest(), APICon, time);
  }

  @Override
  public StationList getAllowStationsList() throws HTTPClientException, ParserException {
    request.setBranch("/stations_list/?");

    jsonParser = new JSONParser();
    Duration time = Duration.ofMinutes(10);

    return jsonParser.parseIntoObj(StationList.class, request.getRequest(), APICon, time);
  }


}




