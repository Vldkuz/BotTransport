package Develop;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class API {

    private final String APIKey;
    private final String APIUrl;

    public API(String APIKey,
            String APIUrl) { // Для работы с API требуется инициализация ключа и url.
        // Добавлена валидация на ключ
        if (APIKey.length() == 0 || APIUrl.length() == 0) {
            throw new IllegalStateException("Ошибка при вводе ключа или URL API");
        }

        this.APIKey = APIKey;
        this.APIUrl = APIUrl;
        // APIurl - общая часть для запросов к API. Далее в каждом методе будем подставлять определенную часть для запроса
    }

    public void getListStation() throws Exception {
        final String sectionStations = "/copyright/?";
        String request = getURL(sectionStations);
        URL objectUrl = new URL(request);
        HttpURLConnection urlCon = (HttpURLConnection) objectUrl.openConnection();
        urlCon.setRequestMethod("GET");
        urlCon.setRequestProperty("Authorization", APIKey);
        urlCon.setReadTimeout(1000000000);

        short statusCode = (short) urlCon.getResponseCode();

        if (statusCode==HttpURLConnection.HTTP_OK){
            InputStream response = urlCon.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null) {
                buf.append(line).append("\n");
            }

            System.out.println(buf);
        }
        else {
            throw new Exception("Ошибка при запросе к API");
        }



    }

    private String getURL(String section) {
        return APIUrl + section;
    }
    //
}
