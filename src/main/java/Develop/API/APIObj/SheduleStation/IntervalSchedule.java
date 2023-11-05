package Develop.API.APIObj.SheduleStation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntervalSchedule {

    private String exceptDays; // except_days
    private Thread thread;
    private boolean isFuzzy; // is_fuzzy
    private String days;
    private String stops;
    private String terminal;
    private String platform;

    public String getExceptDays() {
        return exceptDays;
    }

    @JsonProperty("except_days")
    public void setExceptDays(String exceptDays) {
        this.exceptDays = exceptDays;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isFuzzy() {
        return isFuzzy;
    }

    @JsonProperty("is_fuzzy")
    public void setFuzzy(boolean fuzzy) {
        isFuzzy = fuzzy;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
