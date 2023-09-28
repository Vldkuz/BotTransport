package Develop.API.APIObj.SheduleBetStation;


import java.util.List;

public class SheduleBetStation {
  public IntervalSegments interval_segments;
  public List<Segment> segments;
  public Search search;
}

class IntervalSegments
{
  SegmentsFrom from;
  Thread thread;
  String departure_platform;
  String stops;
  String departure_terminal;
  SegmentsTo to;
  boolean has_transfers;
  TicketsInfo tickets_info;
  String duration;
  String arrival_terminal;
  String start_date;
  String arrival_platform;
}

class Segment
{
  String arrival;
  SegmentsFrom from;
  Thread thread;
  String departure_platform;
  String departure;
  String stops;
  String departure_terminal;
  SegmentsTo to;
  boolean has_transfers;
  TicketsInfo tickets_info;
  String duration;
  String arrival_terminal;
  String start_date;
  String arrival_platform;
}

class TicketsInfo
{
  boolean et_marker;
  List<Place> places;
}

class Place
{
  String currency;
  Price price;
  String name;
}

class Price
{
  int cents;
  int whole;
}

class Thread
{
 String uid;
 String title;
 Interval interval;
 String number;
 String short_title;
 String thread_method_link;
 Carrier carrier;
 String transport_type;
 String vehicle;
 TransportSubType transport_subtype;
 String express_type;
}

class TransportSubType
{
  String color;
  String code;
  String title;
}

class Carrier
{
  String code;
  String contacts;
  String url;
  String logo_svg;
  String title;
  String phone;
  Code codes;
  String address;
  String logo;
  String email;
}

class Code
{
  String icao;
  String sirena;
  String iata;
}



class Interval
{
  String density;
  String end_time;
  String begin_time;
}

class SegmentsFrom
{
  String code;
  String title;
  String station_type;
  String station_type_name;
  String popular_title;
  String short_title;
  String transport_type;
  String type;
}

class SegmentsTo
{
  String code;
  String title;
  String station_type;
  String station_type_name;
  String popular_title;
  String transport_type;
  String type;
}


class Search
{
  String date;
  SearchTo to;
  SearchFrom from;
}

class SearchFrom
{
  String code;
  String type;
  String popular_title;
  String short_title;
  String title;
}

class SearchTo
{
  String code;
  String type;
  String popular_title;
  String short_title;
  String title;
}