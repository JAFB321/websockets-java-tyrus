package com.team4.server;

import com.team4.structures.Message;
import com.team4.structures.MessageDecoder;
import com.team4.structures.MessageEncoder;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@javax.websocket.server.ServerEndpoint(value = "/", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class Server {

    static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Usuario conectado");
        
        //Se añade a la lista
        clients.add(session);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        clients.remove(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        String user = (String) session.getUserProperties().get("user");
        if (user == null) {
            session.getUserProperties().put("user", message.getautor());
        }

        System.out.println("Mensaje recibido: "+message.getContent());
        System.out.println("Autor: "+message.getautor());
        
        //Reenviar 
        for (Session client : clients) {
            if (!session.getId().equals(client.getId())) {
                client.getBasicRemote().sendObject(message);
            }
        }
    }

}
