package Develop;

import java.util.Scanner;
import java.io.PrintStream;

public class Main {

    public static void help(){
        System.out.println("the help code");
    }

    public static void exit(){
        System.out.println("the exit. God buy)");
        System.exit(0);
    }

    public static void failWork(){
        try {
            PrintStream OutFile = new PrintStream("1.txt");
            OutFile.println("text");
            System.out.println("good write");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
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
                case "-F":          //fail
                    failWork();
                    break;
                default:
                    System.out.println("Not have this command. try write key(-h) or white (-exit), if you want leave");
                    break;
            }
            UserString = UsersData.nextLine();
          
        API obj = new API("2e07c9e6-4de0-486d-accd-95e725fd87bc",
                "https://api.rasp.yandex.net/v3.0");
        try {
            obj.getListStation();
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }
}