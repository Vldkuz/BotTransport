package Develop.KeyManager;

public class KeyManagerException extends Exception {
  private final String errorText;
  private final Exception exceptionCaused;

  public KeyManagerException(String errorText, Exception exceptionCaused)
  {
    this.errorText = errorText;
    this.exceptionCaused = exceptionCaused;
  }


  public String getErrorText() {
    return errorText;
  }

  public Exception getExceptionCaused() {
    return exceptionCaused;
  }
}
