package com.bdf.inacap.rest.controller.dto;

import com.bdf.inacap.exception.CodeError;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDTO {

    private String message;
    private CodeError code;

}