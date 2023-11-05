package Develop.API.APIObj.StationList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjStation {

    private String direction;
    private Codes codes;
    private String stationType; // station_type
    private String title;
    private String longitude;
    private String transportType; // transport_type
    private String latitude;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Codes getCodes() {
        return codes;
    }

    public void setCodes(Codes codes) {
        this.codes = codes;
    }

    public String getStationType() {
        return stationType;
    }

    @JsonProperty("station_type")
    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTransportType() {
        return transportType;
    }

    @JsonProperty("transport_type")
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
