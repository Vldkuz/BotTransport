package Develop.URL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnector {

    public UrlConnector(String cAPIKey) {
        if (cAPIKey.length() == 0) {
            throw new RuntimeException("Ключ для API пустой");
        }
        APIKey = cAPIKey;
    }

    public InputStream getInputStream(String request) throws IOException {
        URL objectUrl = new URL(request);
        int attempts = 5;

        while (attempts > 0) {

            try {
                HttpURLConnection urlCon = (HttpURLConnection) objectUrl.openConnection();
                urlCon.setRequestMethod("GET");
                urlCon.setRequestProperty("Authorization", APIKey);

                short statusCode = (short) urlCon.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    return urlCon.getInputStream();
                }
            } catch (IOException NetConProblem) {}

            attempts--;
        }

        throw new IOException("Response Code API is not OK or API is unreachable");
    }

    public InputStream getInputStreamFile(String nameFile) throws FileNotFoundException {
        return new FileInputStream(nameFile);
    }


    private String APIKey;
}

