package Develop.API.APIObj.SheduleStation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thread {

    private String uid;
    private String title;
    private String number;
    private String shortTitle; // short_title
    private Carrier carrier;
    private String transportType; // transport_type

    private String vehicle;
    private TransportSubtype transportSubtype; // transport_subtype
    private String expressType; // express_type

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    @JsonProperty("short_title")
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public String getTransportType() {
        return transportType;
    }

    @JsonProperty("transport_type")
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public TransportSubtype getTransportSubtype() {
        return transportSubtype;
    }

    @JsonProperty("transport_subtype")
    public void setTransportSubtype(TransportSubtype transportSubtype) {
        this.transportSubtype = transportSubtype;
    }

    public String getExpressType() {
        return expressType;
    }

    @JsonProperty("express_type")
    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }
}
