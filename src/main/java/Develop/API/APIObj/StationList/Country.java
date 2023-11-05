package Develop.API.APIObj.StationList;

import java.util.List;

public class Country {

    private Codes codes;
    private String title;
    private List<Regions> regions;

    public Codes getCodes() {
        return codes;
    }

    public void setCodes(Codes codes) {
        this.codes = codes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Regions> getRegions() {
        return regions;
    }

    public void setRegions(List<Regions> regions) {
        this.regions = regions;
    }
}
