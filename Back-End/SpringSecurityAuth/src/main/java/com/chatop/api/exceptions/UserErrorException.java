package com.chatop.api.exceptions;

public class UserErrorException extends RuntimeException {
    public UserErrorException(String error){ super(error);}
    public UserErrorException(String error, Exception e){ super(error,e);}
}
