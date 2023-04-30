package com.favAnime.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {
    // overrides all runtime exceptions
    public InformationExistException(String message) {
        super(message);
    }
}
