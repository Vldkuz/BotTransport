package Develop;

import java.util.Scanner;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        System.out.println("//приветствие");
        Scanner UsersData = new Scanner(System.in);
        String UserString = UsersData.nextLine();

        while (true) {
            switch (UserString) {
                case "-help":   //help
                case "-h":
                case "help":
                case "h":
                    System.out.println("the help code");
                    break;
                case "-exit": //exit
                    System.out.println("the exit. God buy)");
                    System.exit(0);
                    break;
                case "-F": //fail
                    try {
                        PrintStream OutFile = new PrintStream("1.txt");
                        OutFile.println("text");
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                default:
                    System.out.println("Not have this command. try write key(-h) or white (-exit), if you want leave");
                    break;
            }
            UserString = UsersData.nextLine();
        }
    }
}