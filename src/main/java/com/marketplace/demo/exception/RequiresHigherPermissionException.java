package com.marketplace.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RequiresHigherPermissionException extends RuntimeException {

    public RequiresHigherPermissionException(String message) {
        super(message);
    }
}
