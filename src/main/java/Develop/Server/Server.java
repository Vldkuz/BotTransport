package Develop.Server;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.io.OutputStream;

public class Server {

    private static void help(PrintWriter writer) {
        writer.println("the help code");
        writer.flush();
    }

    private static void exit(PrintWriter writer) {
        writer.println("the exit. God buy)");
        writer.flush();
        System.exit(0);
    }

    /*private static void fileWork() {
        try {
            PrintStream OutFile = new PrintStream("1.txt");
            OutFile.println("text");
            System.out.println("good write");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }*/


    private static void showScheduleBetStation(PrintWriter writer) {
        writer.println("Schedule between stations");
        writer.flush();

    }

    private static void showScheduleByStation(PrintWriter writer) {
        writer.println("Schedule by station");
        writer.flush();

    }

    private static void showFollowStations(PrintWriter writer) {
        writer.println("Follow stations");
        writer.flush();

    }

    private static void showNearStation(PrintWriter writer) {
        writer.println("List of nearest stations");
        writer.flush();

    }

    private static void showNeartCity(PrintWriter writer) {
        writer.println("Nearest city");
        writer.flush();
    }

    private static void showInfCarrier(PrintWriter writer) {
        writer.println("Information about the carrier");
        writer.flush();

    }

    public static void run(InputStream in, OutputStream out) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)))) {

            writer.println("//приветствие");
            writer.flush(); // Очистка буфера и запись данных

            String userString;
            do {
                userString = reader.readLine();
                switch (userString) {
                    case "-help":       //help
                    case "-h":
                    case "help":
                    case "h":
                        help(writer);
                        break;
                    case "-exit":       //exit
                        exit(writer);
                        break;
//                case "-F":          //file
//                    fileWork(writer);
//                    break;
                    case "fs":               //Расписание рейсов между станциями
                        showScheduleBetStation(writer);
                        break;
                    case "bs":               //Расписание рейсов по станции
                        showScheduleByStation(writer);
                        break;
                    case "lot":               //Список станций следования
                        showFollowStations(writer);
                        break;
                    case "ns":               //Список ближайших станций
                        showNearStation(writer);
                        break;
                    case "nc":               //Ближайший город
                        showNeartCity(writer);
                        break;
                    case "ci":               //Информация о перевозчике
                        showInfCarrier(writer);
                        break;
                    default:
                        writer.println("Not have this command. try write key(-h) or white (-exit), if you want to leave");
                        writer.flush();
                        break;
                }

            } while (userString != null && !userString.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public static void run(InputStream in, OutputStream out) {
        System.out.println("//приветствие");
        Scanner UsersData = new Scanner(System.in);
        String UserString;

        do {
            UserString = UsersData.nextLine();
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
//                case "-o":          //file
//                    fileWork();
//                    break;
                case "fs":               //Расписание рейсов между станциями
                    showScheduleBetStation();
                    break;
                case "bs":               //Расписание рейсов по станции
                    showScheduleByStation();
                    break;
                case "lot":               //Список станций следования
                    showFollowStations();
                    break;
                case "ns":               //Список ближайших станций
                    showNearStation();
                    break;
                case "nc":               //Ближайший город
                    showNeartCity();
                    break;
                case "ci":               //Информация о перевозчике
                    showInfCarrier();
                    break;
                default:
                    System.out.println("Not have this command. try write key(-h) or white (-exit), if you want leave");
                    break;
            }

        } while (!UserString.isEmpty());

    }*/
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
