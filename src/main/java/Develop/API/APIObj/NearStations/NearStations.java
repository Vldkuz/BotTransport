package Develop.API.APIObj.NearStations;

import java.util.List;

public class NearStations {
  public List<Station> stations;
}
class Station
{
  String distance;
  String code;
  String station_type;
  String station_type_name;
  TypeChoices type_choices;
  String title;
  String popular_title;
  String short_title;
  String majority;
  String transport_type;
  String lat;
  String lng;
  String type;
}


class TypeChoices
{
  int total;
  int limit;
  int offset;
}
