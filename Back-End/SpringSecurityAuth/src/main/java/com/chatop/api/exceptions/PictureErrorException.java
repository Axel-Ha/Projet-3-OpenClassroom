package com.chatop.api.exceptions;

public class PictureErrorException extends RuntimeException{
    public PictureErrorException(String error){
        super(error);
    }

    public PictureErrorException(String error, Exception e ){
        super(error, e);
    }
}
