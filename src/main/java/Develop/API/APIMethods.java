package Develop.API;

import java.io.IOException;

public interface APIMethods {
    public Object getShedule(String to, String from) throws IOException;
    public Object getShedule(String station) throws IOException;
    public Object getFollowList(String uid) throws IOException;
    public Object getNearStations(String latitude,String longitude) throws IOException;
    public Object getNearCity(String latitude,String longitude) throws IOException;
    public Object getInfoCarrier(String code) throws IOException;
    Object getAllowStationsList() throws IOException;
}
