package Develop.API.APIObj.NearCity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NearCity {

  private String distance;
  private String code;
  private String title;
  private String popularTitle; // popular_title
  private String shortTitle; // short_title
  private String lat;
  private String lng;
  private String type;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
}
