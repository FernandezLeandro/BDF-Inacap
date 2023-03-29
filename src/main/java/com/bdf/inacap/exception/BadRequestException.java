package com.bdf.inacap.exception;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
