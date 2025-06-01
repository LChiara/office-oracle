package com.example.officeoracle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import dev.langchain4j.model.ollama.OllamaLanguageModel;

import io.smallrye.common.annotation.Blocking;
import java.util.concurrent.CompletableFuture;
import jakarta.enterprise.context.control.ActivateRequestContext;

import java.time.LocalTime;
import java.time.LocalDate;

@ServerEndpoint("/oracle-chat")
@ApplicationScoped
public class OracleChatSocket {

    @Inject
    OracleAgent oracle;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Session opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        CompletableFuture.runAsync(() -> askOracleAndReply(message, session));
    }

    @ActivateRequestContext
    public void askOracleAndReply(String message, Session session) {
        try {
            String response = oracle.ask(message + ". Consider it is %s at %s.".formatted(
                    LocalDate.now().getDayOfWeek(),
                    LocalTime.now()));
            session.getAsyncRemote().sendText(response);
        } catch (Exception e) {
            session.getAsyncRemote().sendText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session closed: " + session.getId());
    }
}
