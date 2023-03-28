package com.bdf.inacap.rest.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {

    public Long id;
    public String nameCompany;
    public String cuit;
    public String bussinesName;
    public String contactName;
    public int contactNumber;
    public String email;

}