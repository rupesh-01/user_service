package com.example.user_service.exceptions;

public class TokenCountExceededException extends RuntimeException{
    public TokenCountExceededException(String message){
        super(message);
    }
}
