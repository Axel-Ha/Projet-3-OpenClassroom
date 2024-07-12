package com.chatop.api.exceptions;

public class JWTErrorException extends RuntimeException {
    public JWTErrorException(String error){super(error);}
    public JWTErrorException(String error, Exception e){super(error,e);}
}
