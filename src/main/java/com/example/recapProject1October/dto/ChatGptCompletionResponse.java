package com.example.recapProject1October.dto;

import java.util.List;

public record ChatGptCompletionResponse(List<ChatGptChoice> choices) {
}
