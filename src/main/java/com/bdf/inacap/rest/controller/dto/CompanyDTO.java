package com.bdf.inacap.rest.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {

    public Long id;

    @NotEmpty(message = "field cannot be null or empty")
    @NotNull

    public String nameCompany;

    @NotEmpty(message = "field can't be null or empty")
    @NotNull
    public String cuit;

    @NotEmpty(message = "field cannot be null or empty")
    @NotNull
    public String bussinesName;

    public String contactName;

    public int contactNumber;

    public String email;

}