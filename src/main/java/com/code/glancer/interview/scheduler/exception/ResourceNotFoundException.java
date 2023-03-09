package com.code.glancer.interview.scheduler.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResourceNotFoundException extends RuntimeException {

    private String message;
    private HttpStatus code;

    public ResourceNotFoundException(String message, HttpStatus code) {
        this.code = code;
        this.message = message;
    }

}
