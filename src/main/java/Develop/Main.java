package Develop;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("//приветствие");
        Scanner UsersData = new Scanner(System.in);
        String UserString = UsersData.nextLine();
        while (true) {
            if (UserString.equals("-help") | UserString.equals("-h") | UserString.equals("help")) {
                System.out.println("the help code");
                UserString = UsersData.nextLine();
            } else if (UserString.equals("-exit")) {
                System.out.println("the exit");
                System.exit(0);
            }
        }
    }
}