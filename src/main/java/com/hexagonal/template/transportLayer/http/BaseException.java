package com.hexagonal.template.transportLayer.http;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    private final String userMessage;
    private final String developerMessage;
    private final HttpStatus httpStatus;
    private final String origin;

    public String getUserMessage() {
        return this.userMessage;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getOrigin() {
        return this.origin;
    }

    protected BaseException(String userMessage, String developerMessage, HttpStatus httpStatus, String origin) {
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
        this.httpStatus = httpStatus;
        this.origin = origin;
    }

}
