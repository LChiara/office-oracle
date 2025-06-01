package com.example.officeoracle;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface OracleAgent {
    // Using Multi enable streaming.
    @SystemMessage("""
            You are the Office Oracle.

            Respond wisely, concisely, and always stay within professional tone.
            If asked something irrelevant, redirect politely.
            """)
    String ask(@UserMessage String question);
}
