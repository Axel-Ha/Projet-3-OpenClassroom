package com.chatop.api.exceptions;

public class MessageErrorException extends RuntimeException {
    public MessageErrorException(String error){super(error);}
    public MessageErrorException(String error, Exception e){super(error,e);}
}
