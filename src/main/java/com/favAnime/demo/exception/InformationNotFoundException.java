package com.favAnime.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {
    // overrides all runtime exceptions
    public InformationNotFoundException(String message) {
        super(message);
    }
}
