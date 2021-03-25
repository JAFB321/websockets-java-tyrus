package com.team4.client;

import com.team4.structures.Message;
import com.team4.structures.MessageDecoder;
import com.team4.structures.MessageEncoder;
import javax.websocket.OnMessage;

@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class Client {
    
    @OnMessage
    public void onMessage(Message message) {
        System.out.println("\nMensaje recibido: " + message.getContent());
        System.out.println("Autor: " + message.getautor());
        System.out.println("");
    }

}
