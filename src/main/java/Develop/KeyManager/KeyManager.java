package Develop.KeyManager;

import Develop.Main;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class KeyManager {

  private String key;

  public KeyManager(String name) {
    try {
      InputStream iStream = Main.class.getResourceAsStream(name);
      key = IOUtils.toString(iStream, StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Что-то пошло не так при чтении файла");
    }
  }

  public void setKey(String key) {
    this.key = key;
  }
  public String getKey() {
    return key;
  }
}
