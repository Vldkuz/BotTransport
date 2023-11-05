package Develop.API.APIObj.SheduleBetStation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TicketsInfo {

    private boolean etMarker; // et_marker
    private List<Place> places;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public boolean isEtMarker() {
        return etMarker;
    }

    @JsonProperty("et_marker")
    public void setEtMarker(boolean etMarker) {
        this.etMarker = etMarker;
    }
}
