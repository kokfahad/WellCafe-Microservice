package com.fahad.productservice.exception;

import org.springframework.http.HttpStatus;

public class FeignClientExceptionHandler extends BaseException {

    public FeignClientExceptionHandler() {
        this("Exception occurred in FeignClient Call");
    }

    public FeignClientExceptionHandler(String customMessage) {
        this(customMessage, customMessage);
    }

    public FeignClientExceptionHandler(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
        this.code = "5006";
    }

    public FeignClientExceptionHandler(String message, String customMessage, String code) {
        this(message, customMessage);
        this.code = code;
    }

    public FeignClientExceptionHandler(String message, String customMessage, String code, HttpStatus HttpStatus) {
        this(message, customMessage, code);
        this.httpStatus = httpStatus;
    }

    public FeignClientExceptionHandler(String message, String customMessage, HttpStatus httpStatus) {
        this(message, customMessage);
        this.httpStatus = httpStatus;
    }
}
