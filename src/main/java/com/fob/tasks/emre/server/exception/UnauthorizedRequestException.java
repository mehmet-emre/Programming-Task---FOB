package com.fob.tasks.emre.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedRequestException extends RuntimeException {

    /**
     * auto generated serial
     */
    private static final long serialVersionUID = 4309494654383222517L;

    public UnauthorizedRequestException() {
        super("Unauthorized Request!");
    }
}
