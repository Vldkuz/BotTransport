package Develop.API.APIObj.StationList;

import java.util.List;

public class Regions {

    private Codes codes;
    private String title;
    private List<Settlements> settlements;

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

    public List<Settlements> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlements> settlements) {
        this.settlements = settlements;
    }
}
