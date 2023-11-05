package Develop.API.APIObj.FollowStations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FollowStations {

  private String exceptDays; // except_days
  private String arrivalDate; // arrival_date
  private String from;
  private String uid;
  private String title;
  private Interval interval;
  private String departureDate; // departure_date
  private String startTime; // start_time
  private String number;
  private String shortTitle; // short_title
  private String days;
  private String to;
  private Carrier carrier;
  private String transportType; // transport_type
  private List<Stop> stops;
  private String vehicle;
  private String startDate; // start_date
  private TransportSubtype transportSubtype; // transport_subtype
  private String expressType; // express_type

  public String getExceptDays() {
    return exceptDays;
  }

  @JsonProperty("except_days")
  public void setExceptDays(String exceptDays) {
    this.exceptDays = exceptDays;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  @JsonProperty("arrival_date")
  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

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

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  @JsonProperty("departure_date")
  public void setDepartureDate(String departureDate) {
    this.departureDate = departureDate;
  }

  public String getStartTime() {
    return startTime;
  }

  @JsonProperty("start_time")
  public void setStartTime(String startTime) {
    this.startTime = startTime;
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

  public String getDays() {
    return days;
  }

  public void setDays(String days) {
    this.days = days;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
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

  public List<Stop> getStops() {
    return stops;
  }

  public void setStops(List<Stop> stops) {
    this.stops = stops;
  }

  public String getVehicle() {
    return vehicle;
  }

  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

  public String getStartDate() {
    return startDate;
  }

  @JsonProperty("start_date")
  public void setStartDate(String startDate) {
    this.startDate = startDate;
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

