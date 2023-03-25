package com.fahad.productservice.exception;



public class GenericException extends BaseException {

    public GenericException() {
        this("Internal Error Occurred!!!");
    }

    public GenericException(String customMessage) {
        this(customMessage, customMessage);
    }

    public GenericException(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
        this.code = "5005";
    }
}
