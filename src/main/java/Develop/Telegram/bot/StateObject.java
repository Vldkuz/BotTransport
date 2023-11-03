package Develop.Telegram.bot;

public class StateObject {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StateObject(String status) {
        this.status = status;
    }
}
