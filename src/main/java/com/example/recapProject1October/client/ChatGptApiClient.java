package com.example.recapProject1October.client;

import com.example.recapProject1October.dto.ChatGptCompletionRequest;
import com.example.recapProject1October.dto.ChatGptCompletionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
@Component
public class ChatGptApiClient {
    private final RestClient restClient;

    public ChatGptApiClient(RestClient.Builder restClientBuilder,
                            @Value("${chat.gpt.api.token}") String chatGptApiToken) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + chatGptApiToken)
                .build();
    }

    public ChatGptCompletionResponse complete(ChatGptCompletionRequest chatGptCompletionRequest) {
        return restClient.post()
                .uri("/chat/completions")
                .body(chatGptCompletionRequest)
                .retrieve()
                .body(ChatGptCompletionResponse.class);
    }
}
