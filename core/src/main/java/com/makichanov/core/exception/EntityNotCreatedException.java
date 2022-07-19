package com.makichanov.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotCreatedException extends RuntimeException {
    public EntityNotCreatedException() {
    }

    public EntityNotCreatedException(String message) {
        super(message);
    }

    public EntityNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotCreatedException(Throwable cause) {
        super(cause);
    }
}
