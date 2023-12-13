package Develop.Telegram.UserHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InfoHolder {
  private String name;
  private final LinkedList<String> stationHolder = new LinkedList<>();
  private String lastSource;
  private String lastDestination;

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

  public List<String> getLastStation(int n) {
    List<String> list = new ArrayList<String>(n);

    while (!stationHolder.isEmpty() & n > 0)
    {
      String station = stationHolder.removeLast();
      list.add(station);
      n--;
    }

    return list;
  }

  public LinkedList<String> getStationList()
  {
    return stationHolder;
  }

  public int getSizeStationHolder() {return this.stationHolder.size();}
  public String getLastSource() {
    return lastSource;
  }

  public void setLastSource(String lastSource) {
    this.lastSource = lastSource;
  }

  public String getLastDestination() {
    return lastDestination;
  }

  public void setLastDestination(String lastDestination) {
    this.lastDestination = lastDestination;
  }

}
