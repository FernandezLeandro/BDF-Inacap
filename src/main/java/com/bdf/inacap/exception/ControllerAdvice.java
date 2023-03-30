package com.bdf.inacap.exception;

import com.bdf.inacap.rest.controller.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    private StringBuilder getErrorMessage(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getDefaultMessage()));
        return errorMessage;
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorDTO> alreadyExistException(BadRequestException e) {
        ErrorDTO error = ErrorDTO.builder().message(e.getMessage()).code(CodeError.C400).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CompanyException.class)
    public ResponseEntity companiesIsEmpty(CompanyException e) {
        ErrorDTO error = ErrorDTO.builder().message(e.getMessage()).code(e.getCodeError()).build();
        //return new ResponseEntity<>(error, e.getStatus());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}
