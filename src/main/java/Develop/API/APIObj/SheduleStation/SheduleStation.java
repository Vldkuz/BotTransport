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
  String code;
  String station_type;
  String station_type_name;
  String title;
  String popular_title;
  String short_title;

  CodesStation codes;
  String transport_type;

  String type;
}

class CodesStation
{
  String yandex;
  String esr;
}


class Shedule
{
  String except_days;
  String arrival;
  Thread thread;
  boolean is_fuzzy;
  String days;
  String stops;
  String departure;
  String terminal;
  String platform;
}

class Thread
{
  String uid;
  String title;
  String number;
  String short_title;
  Carrier carrier;
  String transport_type;

  String vehicle;
  String transport_subtype;
  String express_type;

}

class Carrier
{
  int code;

}

class CodeCarrier
{
  String icao;
  String sirena;
  String iata;
}


class SheduleDirection
{
  String code;
  String title;
}

class Directions
{
  String code;
  String title;
}


