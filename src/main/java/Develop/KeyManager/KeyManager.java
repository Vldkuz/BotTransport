package Develop.KeyManager;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class KeyManager {

  private final String key;

  public KeyManager(String name) throws KeyManagerException {
    try {
      InputStream iStream = KeyManager.class.getResourceAsStream(name);
      key = IOUtils.toString(iStream, StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new KeyManagerException("Что-то пошло не так при чтении файла", e);
    }
  }
  public String getKey() {
    return key;
  }
}
