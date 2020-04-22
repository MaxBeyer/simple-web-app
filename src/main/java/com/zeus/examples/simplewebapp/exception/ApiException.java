package com.zeus.examples.simplewebapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ApiException extends IllegalStateException {

    private HttpStatus httpStatus;

    public ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
