package Develop.API.APIObj.SheduleBetStation;


import java.util.List;

public class SheduleBetStation {
  public IntervalSegments interval_segments;
  public List<Segment> segments;
  public Search search;
}

class IntervalSegments
{
  public SegmentsFrom from;
  public Thread thread;
  public String departure_platform;
  public String stops;
  public String departure_terminal;
  public SegmentsTo to;
  public boolean has_transfers;
  public TicketsInfo tickets_info;
  public String duration;
  public String arrival_terminal;
  public String start_date;
  public String arrival_platform;
}

class Segment
{
  public String arrival;
  public SegmentsFrom from;
  public Thread thread;
  public String departure_platform;
  public String departure;
  public String stops;
  public String departure_terminal;
  public SegmentsTo to;
  public boolean has_transfers;
  public TicketsInfo tickets_info;
  public String duration;
  public String arrival_terminal;
  public String start_date;
  public String arrival_platform;
}

class TicketsInfo
{
  public boolean et_marker;
  public List<Place> places;
}

class Place
{
  public String currency;
  public Price price;
  public String name;
}

class Price
{
  public int cents;
  public int whole;
}

class Thread
{
  public String uid;
  public String title;
  public Interval interval;
  public String number;
  public String short_title;
  public String thread_method_link;
  public Carrier carrier;
  public String transport_type;
  public String vehicle;
  public TransportSubType transport_subtype;
  public String express_type;
}

class TransportSubType
{
  public String color;
  public String code;
  public String title;
}

class Carrier
{
  public String code;
  public String contacts;
  public String url;
  public String logo_svg;
  public String title;
  public String phone;
  public Code codes;
  public String address;
  public String logo;
  public String email;
}

class Code
{
  public String icao;
  public String sirena;
  public String iata;
}



class Interval
{
  public String density;
  public String end_time;
  public String begin_time;
}

class SegmentsFrom
{
  public String code;
  public String title;
  public String station_type;
  public String station_type_name;
  public String popular_title;
  public String short_title;
  public String transport_type;
  public String type;
}

class SegmentsTo
{
  public String code;
  public String title;
  public String station_type;
  public String station_type_name;
  public String popular_title;
  public String transport_type;
  public String type;
}


class Search
{
  public String date;
  public SearchTo to;
  public SearchFrom from;
}

class SearchFrom
{
  public String code;
  public String type;
  public String popular_title;
  public String short_title;
  public String title;
}

class SearchTo
{
  public String code;
  public String type;
  public String popular_title;
  public String short_title;
  public String title;
}