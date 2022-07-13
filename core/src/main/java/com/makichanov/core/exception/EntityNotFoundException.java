package com.makichanov.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    // TODO: 7/14/22 Кейсы для применения такого конструктора?
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    //TODO: Зачем настолько низкоуровнево?
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
