package com.xbo.studyspring.exception;

public class CustomAsyncRequestTimeoutException extends RuntimeException {
    private static final long serialVersionUID = 8754629185999484614L;

    public CustomAsyncRequestTimeoutException(String uri){
        super(uri);
    }
}