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

  public String density;
  public String end_time;
  public String begin_time;
}

class Stop {

  public String arrival;
  public String departure;
  public String duration;
  public String stop_time;

}

class Station {

  public CodesStation codes;
  public String title;
  public String station_type;
  public String station_type_name;
  public String popular_title;
  public String short_title;
  public String code;
  public String type;
}

class CodesStation {

  public String express;
  public String yandex;
  public String esr;
}

class Carrier {

  public int code;
  public CodesCarriers codes;
  public String title;
}

class CodesCarriers {

  public String icao;
  public String sirena;
  public String iata;
}

class TransportSubtype {

  public String color;
  public String code;
  public String title;
}
