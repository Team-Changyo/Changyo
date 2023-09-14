package com.shinhan.changyo.api.service.util.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException() {
    }

    public ForbiddenException(String s) {
        super(s);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }
}
