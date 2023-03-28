package com.bdf.inacap.domain.mapper;

import com.bdf.inacap.domain.entity.CompanyDE;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "nameCompany", source = "companyDE.name")
    @Mapping(target = "cuit", source = "companyDE.cuit")
    CompanyDTO deToDTO(CompanyDE companyDE);

    @Mapping(target = "name", source = "companyDTO.nameCompany")
    @Mapping(target = "cuit", source = "companyDTO.cuit")
    CompanyDE dtoToDE(CompanyDTO companyDTO);

}