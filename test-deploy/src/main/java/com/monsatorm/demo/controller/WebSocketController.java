package com.monsatorm.demo.controller;

import com.monsatorm.demo.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@CrossOrigin("*")
@Controller
public class WebSocketController {
    private final RequestService requestService;

    private SimpMessagingTemplate messagingTemplate;

    public WebSocketController(RequestService requestService) {
        this.requestService = requestService;
    }

    @MessageMapping("/recent-requests")
    @SendTo("/topic/recent-requests")
    public void handleNewRequest() {
        requestService.broadcastRecentRequests();
    }

    @EventListener
    public void handleWsListeners(SessionConnectedEvent event) {
        String welcomeMessage = "Connection established.";
        messagingTemplate.convertAndSend("/topic/greetings", welcomeMessage);
    }
}
