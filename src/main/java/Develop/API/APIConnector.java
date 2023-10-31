package Develop.API;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import org.apache.http.HttpStatus;

public class APIConnector {

    public APIConnector(String Key) {
        if (Key.length() == 0) {
            throw new RuntimeException("Key is Empty");
        }
        APIKey = new String(Key);
    }

    public InputStream getInputStream(String request) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(10)).build();
        HttpRequest httpReq = HttpRequest.newBuilder(URI.create(request)).headers("Authorization",APIKey).build();

        HttpResponse<InputStream> response;
        response = client.send(httpReq, BodyHandlers.ofInputStream());

        if (response.statusCode() == HttpStatus.SC_OK) {
            return response.body();
        }

        throw new IOException("Response Code is " + response.statusCode());
    }


    private final String APIKey;

}
