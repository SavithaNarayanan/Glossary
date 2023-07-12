package com.renewtrak.glossary.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A generic business exception which will be handled by a generic error handler in Controller.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiException extends RuntimeException {
    @Getter
    private int code = 500;
    private static final String DEFAULT_MESSAGE = "Internal Server Error";

    public ApiException() {
        super(DEFAULT_MESSAGE);
    }

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ApiException(int code, String msg, Exception originalException) {
        super(msg, originalException);
        this.code = code;
    }

    public ApiException(Exception originalException) {
        this(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_MESSAGE, originalException);
    }

    public ApiException(String msg, Exception originalException) {
        this(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, originalException);
    }

}