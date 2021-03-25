package com.team4.test;

import com.team4.client.Client;
import java.net.URI;
import java.util.Scanner;
import javax.json.Json;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

public class TestClient {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            ClientManager client = ClientManager.createClient();
            String message;
            String SERVER = "ws://localhost:8080";
            
            System.out.println("Escribe tu nombre de usuario");
            String user = scanner.nextLine();
            
            // Conectar con el servidor
            Session session = client.connectToServer(Client.class, new URI(SERVER));

            // Enviar mensajes
            do {
                System.out.println("Send a message:");
                message = scanner.nextLine();
                String json = Json.createObjectBuilder()
                        .add("content", message)
                        .add("autor", user)
                        .add("received", "")
                        .build().toString();
                session.getBasicRemote().sendText(json);
            } while (!message.equalsIgnoreCase("exit"));
        } catch (Exception e) {
        }
    }

}
