package Develop.API.APIExceptions;

import java.util.List;

public class ValidationException extends Exception {
  private final String errorText;
  private final List<FieldHolder> HoldersField;

  public ValidationException(String errorText, List<FieldHolder> HoldersField){
    this.errorText = errorText;
    this.HoldersField = HoldersField;
  }
  public String getErrorText() {
    return errorText;
  }

  public List<FieldHolder> getHoldersField() {
    return HoldersField;
  }
}
