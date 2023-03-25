package com.fahad.productservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException {

    protected String code;
    protected String message;
    protected String customMessage;
    protected HttpStatus httpStatus;

    public BaseException() {
    }

    public BaseException code(String code) {
        this.code = code;
        return this;
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }
}
