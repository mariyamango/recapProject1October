package com.example.recapProject1October.service;

import com.example.recapProject1October.client.ChatGptApiClient;
import com.example.recapProject1October.dto.ChatGptCompletionRequest;
import com.example.recapProject1October.dto.ChatGptCompletionResponse;
import com.example.recapProject1October.dto.ChatGptMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatGptService {

    private final ChatGptApiClient chatGptApiClient;

    public String correctText(String content) {
        String messageContent = "Please perform a spelling and grammar correction for the content and return corrected content only. Content: " + content;
        ChatGptCompletionResponse response = chatGptApiClient.complete(
                new ChatGptCompletionRequest(
                        "gpt-4o-mini",
                        List.of(new ChatGptMessage("user", messageContent))
                )
        );
        return response.choices().getFirst().message().content();
    }

}
