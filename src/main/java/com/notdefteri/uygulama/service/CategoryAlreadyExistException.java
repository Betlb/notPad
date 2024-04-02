package com.notdefteri.uygulama.service;


import org.springframework.http.HttpStatus;

public class CategoryAlreadyExistException extends RuntimeException{

    private HttpStatus httpStatus;
    public CategoryAlreadyExistException(HttpStatus httpStatus,String message){
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
