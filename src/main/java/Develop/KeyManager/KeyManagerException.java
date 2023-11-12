package Develop.KeyManager;

public class KeyManagerException extends Exception {
  private String errorText;
  private Exception exceptionCaused;

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
