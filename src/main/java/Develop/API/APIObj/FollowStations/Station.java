package Develop.API.APIObj.FollowStations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Station {

    private CodesStation codes;
    private String title;
    private String stationType; // station_type
    private String stationTypeName; // station_type_name
    private String popularTitle; // popular_title
    private String shortTitle; // short_title
    private String code;
    private String type;

    public CodesStation getCodes() {
        return codes;
    }

    public void setCodes(CodesStation codes) {
        this.codes = codes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getShortTitle() {
        return shortTitle;
    }

    @JsonProperty("short_title")
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
