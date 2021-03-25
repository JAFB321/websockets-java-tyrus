package com.team4.test;

import java.util.Scanner;
import org.glassfish.tyrus.server.Server;

public class TestServer {

    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/", com.team4.server.Server.class);
        Scanner sc = new Scanner(System.in);
        
        try {
            server.start();
            System.out.println("Server running..");
            System.out.println("Press any key to stop..");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
