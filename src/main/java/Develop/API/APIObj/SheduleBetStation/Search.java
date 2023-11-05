package Develop.API.APIObj.SheduleBetStation;

public class Search {

    private String date;
    private SearchTo to;
    private SearchFrom from;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SearchTo getTo() {
        return to;
    }

    public void setTo(SearchTo to) {
        this.to = to;
    }

    public SearchFrom getFrom() {
        return from;
    }

    public void setFrom(SearchFrom from) {
        this.from = from;
    }
}
