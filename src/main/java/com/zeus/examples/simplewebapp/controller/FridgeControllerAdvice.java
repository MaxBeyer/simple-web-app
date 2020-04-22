package com.zeus.examples.simplewebapp.controller;

import com.zeus.examples.simplewebapp.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class FridgeControllerAdvice {

    @ExceptionHandler(ApiException.class)
    ResponseEntity<Map<String, String>> handleApiException(ApiException ex) {
        log.error("http fridge exception status: " + ex.getHttpStatus() + ", message: " + ex.getMessage(), ex);
        Map<String, String> errorMap = Map.of(
                "error", "soda limit error: " + ex.getHttpStatus(),
                "detail", ex.getMessage()
        );
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
