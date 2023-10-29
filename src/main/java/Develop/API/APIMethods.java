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
    SheduleBetStation getShedule(String to, String from, String date, String transportType, String limit, String resultTimezone, String withTransfers) throws IOException, InterruptedException;
    SheduleStation getShedule(String station, String date, String transportType, String direction, String event, String resultTimezone) throws IOException, InterruptedException;
    FollowStations getFollowList(String uid, String from, String to, String date) throws IOException, InterruptedException;
    NearStations getNearStations(String latitude, String longitude, String distance, String stationTypes, String transportTypes, String limit) throws IOException, InterruptedException;
    NearCity getNearCity(String latitude, String longitude, String distance) throws IOException, InterruptedException;
    InfoCarrier getInfoCarrier(String code) throws IOException, InterruptedException;
    StationList getAllowStationsList() throws IOException, InterruptedException;
}
