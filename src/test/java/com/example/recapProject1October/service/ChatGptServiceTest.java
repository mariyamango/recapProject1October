package com.example.recapProject1October.service;

import com.example.recapProject1October.client.ChatGptApiClient;
import com.example.recapProject1October.dto.ChatGptChoice;
import com.example.recapProject1October.dto.ChatGptCompletionRequest;
import com.example.recapProject1October.dto.ChatGptCompletionResponse;
import com.example.recapProject1October.dto.ChatGptMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChatGptServiceTest {

    private final ChatGptApiClient chatGptApiClient = mock(ChatGptApiClient.class);
    private final ChatGptService chatGptService = new ChatGptService(chatGptApiClient);

    @Test
    void correctText_expectCorrectedText() {
        String inputText = "Text with errors and typos.";
        String correctedText = "Text without errors and typos.";
        ChatGptCompletionResponse mockResponse = new ChatGptCompletionResponse(
                List.of(
                        new ChatGptChoice(
                                new ChatGptMessage("user", correctedText)
                        )
                )
        );
        when(chatGptApiClient.complete(any(ChatGptCompletionRequest.class))).thenReturn(mockResponse);
        String result = chatGptService.correctText(inputText);
        assertEquals(correctedText, result);
        verify(chatGptApiClient, times(1)).complete(any(ChatGptCompletionRequest.class));
    }
}