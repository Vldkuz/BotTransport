package Develop.API;

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
import Develop.API.APIServices.ParamBuilder;

public interface APIMethods {

  SheduleBetStation getShedule(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException;

  SheduleStation getSheduleStation(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException;

  FollowStations getFollowList(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException;

  NearStations getNearStations(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException;

  NearCity getNearCity(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException;

  InfoCarrier getInfoCarrier(ParamBuilder params)
      throws HTTPClientException, ParserException, ValidationException;

  StationList getAllowStationsList()
      throws HTTPClientException, ParserException;
}
