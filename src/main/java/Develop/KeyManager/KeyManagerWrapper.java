package Develop.KeyManager;

public class KeyManagerWrapper {

  KeyManagerException except;
  KeyManager key;

  public String getKey() {
    if (except == null) {
      return key.getKey();
    }

    return null;
  }

  public KeyManagerWrapper(String name) {
    try {
      this.key = new KeyManager(name);
    } catch (KeyManagerException exc) {
      except = exc;
    }
  }

  public void setKey(String name) {
    try {
      KeyManager keyManager = new KeyManager(name);
    } catch (KeyManagerException exc) {
      except = exc;
    }
  }

  public KeyManagerException getExcept() {
    return except;
  }
}
