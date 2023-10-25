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
    public SheduleBetStation getShedule(String to, String from)
            throws IOException, InterruptedException;
    public SheduleStation getShedule(String station) throws IOException, InterruptedException;
    public FollowStations getFollowList(String uid) throws IOException, InterruptedException;
    public NearStations getNearStations(String latitude,String longitude)
            throws IOException, InterruptedException;
    public NearCity getNearCity(String latitude,String longitude)
            throws IOException, InterruptedException;
    public InfoCarrier getInfoCarrier(String code) throws IOException, InterruptedException;
    public StationList getAllowStationsList() throws IOException, InterruptedException;
}
