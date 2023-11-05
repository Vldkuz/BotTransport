package Develop.API.APIObj.StationList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Codes {

    private String yandexCode; // yandex_code
    private String esrCode; // esr_code

    public String getYandexCode() {
        return yandexCode;
    }

    @JsonProperty("yandex_code")
    public void setYandexCode(String yandexCode) {
        this.yandexCode = yandexCode;
    }

    public String getEsrCode() {
        return esrCode;
    }

    @JsonProperty("esr_code")
    public void setEsrCode(String esrCode) {
        this.esrCode = esrCode;
    }
}
