package Develop.API;

import java.io.IOException;

public interface APIMethods {
    public Object getShedule(String to, String from);
    public Object getShedule(String station);
    public Object getFollowList(String uid);
    public Object getNearStations(String latitude,String longitude);
    public Object getNearCity(String latitude,String longitude);
    public Object getInfoCarrier(String code);
    public Object getAllowStationsList() throws IOException;
}
