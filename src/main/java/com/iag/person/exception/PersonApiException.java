package com.iag.person.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
public class PersonApiException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public PersonApiException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public PersonApiException(HttpStatus status, String message) {
        super(message, null);
        this.status = status;
    }

}
