package Develop;

public class Main {

    public static void main(String[] args) {
        API obj = new API("2e07c9e6-4de0-486d-accd-95e725fd87bc",
                "https://api.rasp.yandex.net/v3.0");
        try {
            obj.getListStation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}