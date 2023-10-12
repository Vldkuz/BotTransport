package Develop.APIObj;


import java.util.List;

public class StationList {
    public List<Country> countries;
}

class Country {

    public Codes codes;
    public String title;
    public List<Regions> regions;
}

class Regions {

    public Codes codes;
    public String title;
    public List<Settlements> settlements;
}

class Settlements {

    public String title;
    public Codes codes;
    public List<ObjStation> stations;
}

class ObjStation {

    public String direction;
    public Codes codes;
    public String station_type;
    public String title;
    public String longitude;
    public String transport_type;
    public String latitude;
}

class Codes {

    public String yandex_code;
    public String esr_code;
}



