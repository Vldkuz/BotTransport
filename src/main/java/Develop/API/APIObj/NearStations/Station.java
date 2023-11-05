package Develop.API.APIObj.NearStations;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Station {

    private String distance;
    private String code;
    private String stationType; // station_type
    private String stationTypeName; // station_type_name
    private TypeChoices typeChoices; // type_choices
    private String title;
    private String popularTitle; // popular_title
    private String shortTitle; // short_title
    private String majority;
    private String transportType; //transport_type
    private String lat;
    private String lng;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getMajority() {
        return majority;
    }

    public void setMajority(String majority) {
        this.majority = majority;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public TypeChoices getTypeChoices() {
        return typeChoices;
    }

    @JsonProperty("type_choices")
    public void setTypeChoices(TypeChoices typeChoices) {
        this.typeChoices = typeChoices;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTransportType() {
        return transportType;
    }

    @JsonProperty("transport_type")
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }
}
