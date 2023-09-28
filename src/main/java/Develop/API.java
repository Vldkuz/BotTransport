package Develop;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class API {
    private final String APIKey;
    private final String APIUrl;

    public API(String APIKey, String APIUrl) { // Для работы с API требуется инициализация ключа и url.
        // Добавлена валидация на ключ
        if (APIKey.length()==0 || APIUrl.length()==0)
            throw new IllegalStateException("Ошибка при вводе ключа или URL API");

        this.APIKey = "apikey={" + APIKey + "}"; // Костыль для формирования GET запросов, чтобы каждый раз не формировать строчку с APIKey
        this.APIUrl = APIUrl;
        // APIurl - общая часть для запросов к API. Далее в каждом методе будем подставлять определенную часть для запроса
    }

    public void getListStation() throws Exception {
        final String sectionStations ="/stations_list/?";
        String request = getURL(sectionStations);
        URL objectUrl = new URL(request);
        HttpURLConnection urlCon = (HttpURLConnection) objectUrl.openConnection();
        short statusCode = (short) urlCon.getResponseCode();

        if (statusCode >= 400 && statusCode < 500)
            throw new Exception("Ошибка при запросе в клиентской части");
        if (statusCode >= 500 && statusCode < 600)
            throw new Exception("Ошибка при запросе в серверной части");

        Map<String, List<String>> responseObj = urlCon.getHeaderFields();
        Set<String> fields = responseObj.keySet();


    }

    private String getURL(String section) {
        return APIUrl+section+APIKey;
    }
    //
}
