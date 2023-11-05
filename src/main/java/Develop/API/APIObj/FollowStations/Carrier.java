package Develop.API.APIObj.FollowStations;

public class Carrier {

    private int code;
    private CodesCarriers codes;
    private String title;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CodesCarriers getCodes() {
        return codes;
    }

    public void setCodes(CodesCarriers codes) {
        this.codes = codes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
