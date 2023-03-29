package com.bdf.inacap.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CompanyException extends RuntimeException{
    private HttpStatus status;
    private String message;
    private CodeError codeError;

    public CompanyException(){
        this.message = "Message error is not defined";
    }

    public CompanyException (HttpStatus status, String message, CodeError codeError){
        this.status = status;
        this.message = message;
        this.codeError = codeError;
    }
}
