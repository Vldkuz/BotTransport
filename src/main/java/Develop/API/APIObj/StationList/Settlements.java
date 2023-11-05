package Develop.API.APIObj.StationList;

import java.util.List;

public class Settlements {

    private String title;
    private Codes codes;
    private List<ObjStation> stations;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Codes getCodes() {
        return codes;
    }

    public void setCodes(Codes codes) {
        this.codes = codes;
    }

    public List<ObjStation> getStations() {
        return stations;
    }

    public void setStations(List<ObjStation> stations) {
        this.stations = stations;
    }
}
