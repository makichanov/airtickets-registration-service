package com.makichanov.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotCreatedException extends RuntimeException {
    // TODO: 7/26/22 не много ли перегруженных конструкторов, учитывая то, что они не юзаются в большинстве своем?
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
