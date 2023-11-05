package Develop.API.APIObj.SheduleStation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SheduleStation {
  private String date;
  private Station station;
  private String event;
  private List<Shedule> schedule;
  private List<IntervalSchedule> intervalShedule; // interval_shedule
  private SheduleDirection sheduleDirection; // shedule_direction
  private Directions directions;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Station getStation() {
    return station;
  }

  public void setStation(Station station) {
    this.station = station;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public List<Shedule> getSchedule() {
    return schedule;
  }

  public void setSchedule(List<Shedule> schedule) {
    this.schedule = schedule;
  }

  public List<IntervalSchedule> getIntervalShedule() {
    return intervalShedule;
  }

  @JsonProperty("interval_shedule")
  public void setIntervalShedule(
          List<IntervalSchedule> intervalShedule) {
    this.intervalShedule = intervalShedule;
  }

  public SheduleDirection getSheduleDirection() {
    return sheduleDirection;
  }

  @JsonProperty("shedule_direction")
  public void setSheduleDirection(SheduleDirection sheduleDirection) {
    this.sheduleDirection = sheduleDirection;
  }

  public Directions getDirections() {
    return directions;
  }

  public void setDirections(Directions directions) {
    this.directions = directions;
  }
}


