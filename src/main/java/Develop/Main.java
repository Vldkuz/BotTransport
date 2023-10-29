package Develop;

import Develop.Server.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(System.in,System.out);
        server.run();
    }
}
