package Develop.Telegram.UserHolder;

import java.util.Stack;

public class InfoHolder {
  private String name;
  private Stack<String> stationHolder = new Stack<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String popStation ()
  {
    return stationHolder.pop();
  }

  public boolean hasStation()
  {
    return !stationHolder.empty();
  }

  public void pushStation(String station) {
    stationHolder.push(station);
  }

  // Вся информация по пользователю, включая топингу
}
