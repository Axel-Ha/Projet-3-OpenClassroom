package com.chatop.api.exceptions;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String error) {
        super(error);
    }
    public RentalNotFoundException(String error, Exception e ) {
        super(error, e);
    }
}