package Develop.API.APIObj.SheduleStation;

public class Carrier {

    private int code;
    private String title;
    private Codes codes;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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
}
