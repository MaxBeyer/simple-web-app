package com.zeus.examples.simplewebapp.controller;

import com.zeus.examples.simplewebapp.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridgeControllerAdviceTest {
    @Autowired
    FridgeControllerAdvice advice = new FridgeControllerAdvice();

    @Test
    void handleHttpClientErrorException(){
        var ex = new ApiException(HttpStatus.UNAUTHORIZED, "test message");
        var result = advice.handleApiException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
