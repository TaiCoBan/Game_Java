package com.utc.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BAD_REQUEST(400, "Parameter error", HttpStatus.BAD_REQUEST),
    FORBIDDEN(403, "Forbidden error", HttpStatus.FORBIDDEN),
    NOT_FOUND(404, "Not found exception", HttpStatus.NOT_FOUND),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
