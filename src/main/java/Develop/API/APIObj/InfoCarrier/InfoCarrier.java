package Develop.API.APIObj.InfoCarrier;

public class InfoCarrier {
  public Carrier carriers;
}

class Carrier
{
  int code;
  String contacts;
  String url;
  String title;
  String phone;
  Codes codes;
  String address;
  String logo;
  String email;
}

class Codes {
  String icao;
  String sirena;
  String iata;
}
