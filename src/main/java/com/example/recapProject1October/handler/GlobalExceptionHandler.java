package com.example.recapProject1October.handler;

import com.example.recapProject1October.model.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Some error has occurred. Please contact the administrator.";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleUnexpectedExceptions(Exception e, HttpServletRequest request) {
        logException(e);
        return new ErrorMessage(INTERNAL_SERVER_ERROR_MESSAGE, LocalDateTime.now(), request.getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleExpectedExceptions(BadRequestException e, HttpServletRequest request) {
        logException(e);
        return new ErrorMessage(e.getMessage(), LocalDateTime.now(), request.getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundExceptions(NullPointerException e, HttpServletRequest request) {
        logException(e);
        return new ErrorMessage(e.getMessage(), LocalDateTime.now(), request.getRequestURI());
    }

    private static void logException(Exception e) {
        log.error("Exception caught in global exception handler", e);
    }
}