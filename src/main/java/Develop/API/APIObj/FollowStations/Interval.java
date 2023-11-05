package Develop.API.APIObj.FollowStations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Interval {

    private String density;
    private String endTime; // end_time;
    private String beginTime; // begin_time

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("end_time")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    @JsonProperty("begin_time")
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}
