package Develop.API.APIObj.InfoCarrier;

public class InfoCarrier {
  public Carrier carriers;
}

class Carrier
{
  public int code;
  public String contacts;
  public String url;
  public String title;
  public String phone;
  public Codes codes;
  public String address;
  public String logo;
  public String email;
}

class Codes {
  public String icao;
  public String sirena;
  public String iata;
}
