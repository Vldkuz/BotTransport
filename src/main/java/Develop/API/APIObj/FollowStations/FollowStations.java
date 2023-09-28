package Develop.API.APIObj.FollowStations;

import java.util.List;

public class FollowStations {

  public String except_days;
  public String arrival_date;
  public String from;
  public String uid;
  public String title;
  public Interval interval;
  public String departure_date;
  public String start_time;
  public String number;
  public String short_title;
  public String days;
  public String to;
  public Carrier carrier;
  public String transport_type;
  public List<Stop> stops;
  public String vehicle;
  public String start_date;
  public TransportSubtype transport_subtype;
  public String express_type;
}

class Interval {

  String density;
  String end_time;
  String begin_time;
}

class Stop {

  String arrival;
  String departure;
  String duration;
  String stop_time;

}

class Station {

  CodesStation codes;
  String title;
  String station_type;
  String station_type_name;
  String popular_title;
  String short_title;
  String code;
  String type;
}

class CodesStation {

  String express;
  String yandex;
  String esr;
}

class Carrier {

  int code;
  CodesCarriers codes;
  String title;
}

class CodesCarriers {

  String icao;
  String sirena;
  String iata;
}

class TransportSubtype {

  String color;
  String code;
  String title;
}
