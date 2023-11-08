package Develop.API;

import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIObj.StationList.StationList;
import java.io.IOException;

public interface APIMethods {

  SheduleBetStation getShedule(ParamBuilder params) throws IOException, InterruptedException;

  SheduleStation getSheduleStation(ParamBuilder params) throws IOException, InterruptedException;

  FollowStations getFollowList(ParamBuilder params) throws IOException, InterruptedException;

  NearStations getNearStations(ParamBuilder params) throws IOException, InterruptedException;

  NearCity getNearCity(ParamBuilder params) throws IOException, InterruptedException;

  InfoCarrier getInfoCarrier(ParamBuilder params) throws IOException, InterruptedException;

  StationList getAllowStationsList() throws IOException, InterruptedException;
}
