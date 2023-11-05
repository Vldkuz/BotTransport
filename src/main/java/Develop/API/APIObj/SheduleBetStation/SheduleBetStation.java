package Develop.API.APIObj.SheduleBetStation;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SheduleBetStation {

  private List<IntervalSegments> intervalSegments; // interval_segments
  private List<Segment> segments;
  private Search search;

  public List<IntervalSegments> getIntervalSegments() {
    return intervalSegments;
  }

  @JsonProperty("interval_segments")
  public void setIntervalSegments(
          List<IntervalSegments> intervalSegments) {
    this.intervalSegments = intervalSegments;
  }

  public List<Segment> getSegments() {
    return segments;
  }

  public void setSegments(List<Segment> segments) {
    this.segments = segments;
  }

  public Search getSearch() {
    return search;
  }

  public void setSearch(Search search) {
    this.search = search;
  }
}


