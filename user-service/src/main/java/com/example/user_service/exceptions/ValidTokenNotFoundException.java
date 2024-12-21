package com.example.user_service.exceptions;

public class ValidTokenNotFoundException extends RuntimeException{

    public ValidTokenNotFoundException(String message) {
        super(message);
    }

    public ValidTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidTokenNotFoundException(Throwable cause) {
        super(cause);
    }

    public ValidTokenNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
