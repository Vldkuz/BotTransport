package Develop.API.APIServices;

import Develop.API.APIExceptions.FieldHolder;
import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Null;
import org.apache.http.HttpStatus;

public class APIConnector {

  public APIConnector(String key) throws ValidationException {

    if (key.isEmpty()) {
      FieldHolder errorKey = new FieldHolder("APIKey", key);
      List<FieldHolder> holdersField = new ArrayList<>();
      holdersField.add(errorKey);
      throw new ValidationException("Ошибка валидации ключа", holdersField);
    }

    APIKey = key;
  }

  public InputStream getInputStream(String request, Duration time) throws HTTPClientException {
    HttpClient client = HttpClient.newBuilder().connectTimeout(time).build();
    HttpRequest httpReq = HttpRequest.newBuilder(URI.create(request))
        .headers("Authorization", APIKey).build();

    if (APIKey.isEmpty()) {
      throw new HTTPClientException("Поле ключа пустое");
    }

    HttpResponse<InputStream> response;

    try {
      response = client.send(httpReq, BodyHandlers.ofInputStream());
    } catch (IOException except) {
      throw new HTTPClientException("Ошибка при работе HTTP клиента", except);
    } catch (InterruptedException except) {
      Thread.currentThread().interrupt();
      throw new HTTPClientException("Ошибка при работе с потоками HTTP клиента", except);
    }

    if (response.statusCode() == HttpStatus.SC_OK) {
      return response.body();
    }

    throw new HTTPClientException("Код ответа - " + response.statusCode());
  }


  private final String APIKey;

}
