package com.bdf.inacap.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultStatus {

    public static String ERROR = "error";
    public static String SUCCESS = "success";
    private String status;
    private boolean hasToBeReaded = false;
    private String message;


    public ResultStatus(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public String getErrorMessage() {
        this.setHasToBeReaded(false);
        return this.getMessage();
    }

    public void setMessage(String message) {
        this.setHasToBeReaded(true);
        this.message = message;
    }

}

