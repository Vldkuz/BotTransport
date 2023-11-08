package Develop.API.APIExceptions;

public class HTTPClientException extends Exception {

  private String errorText;
  private Exception exceptionCaused = null;

  public HTTPClientException(String errorText, Exception exceptionCaused) {
    this.errorText = errorText;
    this.exceptionCaused = exceptionCaused;
  }

  public HTTPClientException(String errorText)
  {
    this.errorText = errorText;
  }

  public String getErrorText() {
    return errorText;
  }

  public Exception getExceptionCaused() {
    return exceptionCaused;
  }
}
