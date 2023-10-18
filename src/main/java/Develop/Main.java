package Develop;

import Develop.API.API;
import Develop.Server.Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        Server.run(System.in, System.out);
    }
}
//Расписание рейсов между станциями
//Расписание рейсов по станции
//Список станций следования
//Список ближайших станций
//Ближайший город
//Информация о перевозчике

//Flight schedule between stations
//Flight schedule by station
//List of train stations
//List of nearest stations
//Nearest city
//Information about the carrier