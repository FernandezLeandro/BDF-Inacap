package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

