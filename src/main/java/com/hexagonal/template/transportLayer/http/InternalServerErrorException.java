package com.hexagonal.template.transportLayer.http;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends BaseException {

    public InternalServerErrorException(String userMessage, String developerMessage, String origin) {
        super(userMessage, developerMessage, HttpStatus.INTERNAL_SERVER_ERROR, origin);
    }

    public InternalServerErrorException(String userMessage, HttpStatus httpStatus) {
        super(userMessage, userMessage, httpStatus, "api");
    }

}
