package Develop.API;

import Develop.API.APIObj.FollowStations.FollowStations;
import Develop.API.APIObj.InfoCarrier.InfoCarrier;
import Develop.API.APIObj.NearCity.NearCity;
import Develop.API.APIObj.NearStations.NearStations;
import Develop.API.APIObj.SheduleBetStation.SheduleBetStation;
import Develop.API.APIObj.SheduleStation.SheduleStation;
import Develop.API.APIObj.StationList.StationList;
import java.io.IOException;
import java.lang.ScopedValue.Carrier;

public interface APIMethods {
    public SheduleBetStation getShedule(String to, String from) throws IOException;
    public SheduleStation getShedule(String station) throws IOException;
    public FollowStations getFollowList(String uid) throws IOException;
    public NearStations getNearStations(String latitude,String longitude) throws IOException;
    public NearCity getNearCity(String latitude,String longitude) throws IOException;
    public InfoCarrier getInfoCarrier(String code) throws IOException;
    StationList getAllowStationsList() throws IOException;
}
