package com.example.recapProject1October.model;

import java.time.LocalDateTime;

public record ErrorMessage(String message, LocalDateTime timestamp, String path) {}
