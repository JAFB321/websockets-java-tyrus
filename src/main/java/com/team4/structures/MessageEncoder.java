package com.team4.structures;


import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.json.Json;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("content",message.getContent())
                .add("autor",message.getautor())
                .build()
                .toString();
    }
 
}
