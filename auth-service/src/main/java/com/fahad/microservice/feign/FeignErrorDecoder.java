package com.fahad.microservice.feign;

import com.fahad.microservice.exceptions.FeignClientExceptionHandler;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {

    private final String CUSTOM_COMMON_MESSAGE = "Exception occurred in FeignClient call!";
    private final String COMMON_MESSAGE_FORMAT = "Exception Occurred At: %s\n HTTPStatus: %s\n Message: %s";

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            throw new FeignClientExceptionHandler(
                    String.format(COMMON_MESSAGE_FORMAT, s, response.status(),
                            (response.body() != null ? response.body().toString() : "Not Found Exception Occurred!")),
                    String.format(this.CUSTOM_COMMON_MESSAGE + " Details: %s",
                            (response.body() != null ? response.body().toString() : "Not Found Exception Occurred!")),
                    HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()));
        }

        if (response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            throw new FeignClientExceptionHandler(
                    String.format(COMMON_MESSAGE_FORMAT, s, response.status(),
                            (response.body() != null ? response.body().toString() : "Dependent service unavailable!")),
                    String.format(this.CUSTOM_COMMON_MESSAGE + " Details: %s",
                            (response.body() != null ? response.body().toString() : "Dependent service unavailable!")),
                    HttpStatus.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
        }

        if (response.status() == HttpStatus.METHOD_NOT_ALLOWED.value()) {
            throw new FeignClientExceptionHandler(
                    String.format(COMMON_MESSAGE_FORMAT, s, response.status(),
                            (response.body() != null ? response.body().toString() : "Method not allowed")),
                    String.format(CUSTOM_COMMON_MESSAGE + " Details: %s",
                            (response.body() != null ? response.body().toString() : "Method not allowed")),
                    HttpStatus.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
        }

        if (response.status() == HttpStatus.UNAUTHORIZED.value()) {
            throw new FeignClientExceptionHandler(
                    String.format(COMMON_MESSAGE_FORMAT, s, response.status(),
                            (response.body() != null ? response.body().toString() : "Un-Authenticated Request")),
                    String.format(this.CUSTOM_COMMON_MESSAGE + " Details: %s",
                            (response.body() != null ? response.body().toString() : "Un-Authenticated Request")),
                    HttpStatus.valueOf(HttpStatus.UNAUTHORIZED.value()));
        }

        return new FeignClientExceptionHandler(
                String.format(COMMON_MESSAGE_FORMAT, s, response.status(),
                        (response.body() != null ? response.body().toString() : CUSTOM_COMMON_MESSAGE)),
                String.format(CUSTOM_COMMON_MESSAGE + " Details: %s",
                        (response.body() != null ? response.body().toString() : CUSTOM_COMMON_MESSAGE)),
                HttpStatus.valueOf(response.status()));
    }
}