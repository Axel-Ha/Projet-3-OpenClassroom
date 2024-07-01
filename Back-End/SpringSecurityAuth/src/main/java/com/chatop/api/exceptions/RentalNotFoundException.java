package com.chatop.api.exceptions;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException() {
        super("Could not find the rental");
    }
}