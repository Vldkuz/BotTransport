package Develop.API.APIServices;

import Develop.API.APIExceptions.HTTPClientException;
import Develop.API.APIExceptions.ParserException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

public class JSONParser {

  private final ObjectMapper objMap;
  boolean failUnknownProperties = false;

  public JSONParser(boolean failUnknownProperties) {
    objMap = new ObjectMapper();
    objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failUnknownProperties);
  }

  public JSONParser() {
    objMap = new ObjectMapper();
    objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failUnknownProperties);
  }


  public <T> T parseIntoObj(Class<T> objClass, String request, APIConnector api, Duration time)
      throws ParserException, HTTPClientException {
    try {
      InputStream iStream = api.getInputStream(request, time);
      return (T) objMap.readValue(iStream, objClass);
    } catch (IOException exceptParse) {
      throw new ParserException("Ошибка при парсинге JSON", exceptParse);
    }
  }

}
