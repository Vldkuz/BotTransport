package Develop.Telegram.UserHolder;

import java.util.LinkedList;

public class InfoHolder {
  private String name;
  private final LinkedList<String> stationHolder = new LinkedList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String popStation() {return stationHolder.removeLast();}
  public void pushStation(String station){if (!stationHolder.contains(station)) {stationHolder.addLast(station);}}
  public boolean hasStation()
  {
    return !stationHolder.isEmpty();
  }

  // Вся информация по пользователю, включая топингу
}
