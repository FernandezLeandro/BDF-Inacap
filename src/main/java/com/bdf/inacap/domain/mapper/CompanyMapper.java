package com.bdf.inacap.domain.mapper;

import com.bdf.inacap.domain.bo.CompanyBO;
import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "nameCompany", source = "name")
    @Mapping(target = "cuit", source = "cuit")
    CompanyDTO deToDTO(CompanyDE companyDE);

    @Mapping(target = "name", source = "nameCompany")
    @Mapping(target = "cuit", source = "cuit")
    CompanyDE dtoToDE(CompanyDTO companyDTO);

    @Mapping(target = "nameCompany", source = "name")
    @Mapping(target = "cuit", source = "cuit")
    CompanyBO deToBO(CompanyDE companyDE);

    @Mapping(target = "name", source = "nameCompany")
    @Mapping(target = "cuit", source = "cuit")
    CompanyDE boToDE(CompanyBO companyBO);


}