package com.energizer.auto_uz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityConflictException extends RuntimeException {
    public EntityConflictException() {
    }

    public EntityConflictException(String message) {
        super(message);
    }

    public EntityConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityConflictException(Throwable cause) {
        super(cause);
    }
}
