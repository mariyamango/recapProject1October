package com.example.recapProject1October.dto;

import java.util.List;

public record ChatGptCompletionRequest(String model, List<ChatGptMessage> messages) {
}
