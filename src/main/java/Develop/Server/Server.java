package Develop.Server;

import java.io.PrintStream;
import java.util.Scanner;

public class Server {
    private static void help() {
        System.out.println("the help code");
    }

    private static void exit() {
        System.out.println("the exit. God buy)");
        System.exit(0);
    }

    private static void fileWork() {
        try {
            PrintStream OutFile = new PrintStream("1.txt");
            OutFile.println("text");
            System.out.println("good write");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }


    private static void beSt() {
        System.out.println("Flight schedule between stations");

    }

    private static void bySt() {
        System.out.println("Flight schedule by station");

    }

    private static void LoT() {
        System.out.println("List of train stations");

    }

    private static void LnT() {
        System.out.println("List of nearest stations");

    }

    private static void nC() {
        System.out.println("Nearest city");

    }

    private static void iC() {
        System.out.println("Information about the carrier");

    }

    public static void run() {
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

/*
private static void fs() {
    System.out.println("Flight schedule between stations");
}

private static void bs() {
    System.out.println("Flight schedule by station");
}

private static void los() {
    System.out.println("List of train stations");
}

private static void ns() {
    System.out.println("List of nearest stations");
}

private static void nc() {
    System.out.println("Nearest city");
}

private static void ci() {
    System.out.println("Information about the carrier");
}
 */
