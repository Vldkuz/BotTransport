package Develop;

import java.util.Scanner;
import java.io.PrintStream;

public class Main {

    public static void help() {
        System.out.println("the help code");
    }

    public static void exit() {
        System.out.println("the exit. God buy)");
        System.exit(0);
    }

    public static void fileWork() {
        try {
            PrintStream OutFile = new PrintStream("1.txt");
            OutFile.println("text");
            System.out.println("good write");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void beSt() {
        System.out.println("Flight schedule between stations");

    }

    public static void bySt() {
        System.out.println("Flight schedule by station");

    }

    public static void LoT() {
        System.out.println("List of train stations");

    }

    public static void LnT() {
        System.out.println("List of nearest stations");

    }

    public static void nC() {
        System.out.println("Nearest city");

    }

    public static void iC() {
        System.out.println("Information about the carrier");

    }

    public static void main(String[] args) {
        System.out.println("//приветствие");
        Scanner UsersData = new Scanner(System.in);
        String UserString = UsersData.nextLine();

        while (true) {
            switch (UserString) {
                case "-help":       //help
                case "-h":
                case "help":
                case "h":
                    help();
                    break;
                case "-exit":       //exit
                    exit();
                    break;
                case "-F":          //file
                    fileWork();
                    break;
                case "beSt":               //Расписание рейсов между станциями
                    beSt();
                    break;
                case "bySt":               //Расписание рейсов по станции
                    bySt();
                    break;
                case "LoT":               //Список станций следования
                    LoT();
                    break;
                case "LnT":               //Список ближайших станций
                    LnT();
                    break;
                case "nC":               //Ближайший город
                    nC();
                    break;
                case "iC":               //Информация о перевозчике
                    iC();
                    break;
                default:
                    System.out.println("Not have this command. try write key(-h) or white (-exit), if you want leave");
                    break;
            }
            UserString = UsersData.nextLine();
        }
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