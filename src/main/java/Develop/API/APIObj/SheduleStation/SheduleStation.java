package Develop.API.APIObj.SheduleStation;

import java.util.List;

public class SheduleStation {
  public String date;
  public Station station;
  public List<Shedule> shedule;
  public SheduleDirection shedule_direction;
  public Directions directions;
}

class Station
{
  public String code;
  public String station_type;
  public String station_type_name;
  public String title;
  public String popular_title;
  public String short_title;

  public CodesStation codes;
  public String transport_type;

  public String type;
}

class CodesStation
{
  public String yandex;
  public String esr;
}


class Shedule
{
  public String except_days;
  public String arrival;
  public Thread thread;
  public boolean is_fuzzy;
  public String days;
  public String stops;
  public String departure;
  public String terminal;
  public String platform;
}

class Thread
{
  public String uid;
  public String title;
  public String number;
  public String short_title;
  public Carrier carrier;
  public String transport_type;

  public String vehicle;
  public String transport_subtype;
  public String express_type;

}

class Carrier
{
  public int code;

}

class CodeCarrier
{
  public String icao;
  public String sirena;
  public String iata;
}


class SheduleDirection
{
  public String code;
  public String title;
}

class Directions
{
  public String code;
  public String title;
}


