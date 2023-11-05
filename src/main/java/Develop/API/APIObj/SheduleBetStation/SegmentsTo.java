package Develop.API.APIObj.SheduleBetStation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SegmentsTo {

    private String code;
    private String title;
    private String stationType; // station_type
    private String stationTypeName; // station_type_name
    private String popularTitle; // popular_title
    private String transport_type; // transport_type
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTransport_type() {
        return transport_type;
    }

    @JsonProperty("transport_type")
    public void setTransport_type(String transport_type) {
        this.transport_type = transport_type;
    }

    public String getStationType() {
        return stationType;
    }

    @JsonProperty("station_type")
    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getStationTypeName() {
        return stationTypeName;
    }

    @JsonProperty("station_type_name")
    public void setStationTypeName(String stationTypeName) {
        this.stationTypeName = stationTypeName;
    }

    public String getPopularTitle() {
        return popularTitle;
    }

    @JsonProperty("popular_title")
    public void setPopularTitle(String popularTitle) {
        this.popularTitle = popularTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
