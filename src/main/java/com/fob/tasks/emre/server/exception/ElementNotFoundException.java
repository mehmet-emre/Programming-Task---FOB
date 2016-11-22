package com.fob.tasks.emre.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {

    /**
     * auto generated serial
     */
    private static final long serialVersionUID = 6758897226380420203L;

    public ElementNotFoundException(String message) {
        super(message);
    }
}
