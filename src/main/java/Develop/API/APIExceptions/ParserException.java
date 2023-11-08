package Develop.API.APIExceptions;

public class ParserException extends Exception{
  private final String errorText;
  private Exception exceptionCaused;

  public ParserException(String errorText, Exception exceptionCaused) {
    this.errorText = errorText;
    this.exceptionCaused = exceptionCaused;
  }

  public ParserException(String errorText)
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
