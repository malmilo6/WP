package com.monsatorm.demo.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ErrorMessage;

public class WebSocketChannelInterceptor implements ChannelInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketChannelInterceptor.class);

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        // Check if the message is an error message
        if (message instanceof ErrorMessage errorMessage) {
            logger.error("WebSocket Error: ", errorMessage.getPayload());
        }
    }
}
