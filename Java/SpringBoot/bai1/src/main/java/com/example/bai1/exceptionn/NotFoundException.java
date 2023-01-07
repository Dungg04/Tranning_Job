package com.example.bai1.exceptionn;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
