package com.iag.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public enum PersonApiErrorMapping {

    API_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Investigating the reason"),
    API_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
    API_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
    API_FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden"),
    API_NOT_FOUND(HttpStatus.NOT_FOUND, "Not Found");

    private HttpStatus status;
    private String message;

    PersonApiErrorMapping(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static PersonApiErrorMapping getByStatus(HttpStatus status) {
        return Arrays.stream(PersonApiErrorMapping.values())
                .filter(value -> value.status == status)
                .findFirst()
                .orElse(null);
    }
}
