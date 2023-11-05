package Develop.API.APIObj.SheduleBetStation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Segment {

    private String arrival;
    private SegmentsFrom from;
    private Thread thread;
    private String departurePlatform; // departure_platform
    private String departure;
    private String stops;
    private String departureTerminal; // departure_terminal
    private SegmentsTo to;
    private boolean hasTransfers; // has_transfers
    private TicketsInfo ticketsInfo; // tickets_info
    private String duration;
    private String arrivalTerminal; // arrival_terminal
    private String startDate; // start_date;
    private String arrivalPlatform; // arrival_platform

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public SegmentsFrom getFrom() {
        return from;
    }

    public void setFrom(SegmentsFrom from) {
        this.from = from;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getDeparturePlatform() {
        return departurePlatform;
    }

    @JsonProperty("departure_platform")
    public void setDeparturePlatform(String departurePlatform) {
        this.departurePlatform = departurePlatform;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    @JsonProperty("departure_terminal")
    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public SegmentsTo getTo() {
        return to;
    }

    public void setTo(SegmentsTo to) {
        this.to = to;
    }

    public boolean isHasTransfers() {
        return hasTransfers;
    }

    @JsonProperty("has_transfers")
    public void setHasTransfers(boolean hasTransfers) {
        this.hasTransfers = hasTransfers;
    }

    public TicketsInfo getTicketsInfo() {
        return ticketsInfo;
    }

    @JsonProperty("tickets_info")
    public void setTicketsInfo(TicketsInfo ticketsInfo) {
        this.ticketsInfo = ticketsInfo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    @JsonProperty("arrival_terminal")
    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getArrivalPlatform() {
        return arrivalPlatform;
    }

    @JsonProperty("arrival_platform")
    public void setArrivalPlatform(String arrivalPlatform) {
        this.arrivalPlatform = arrivalPlatform;
    }
}
