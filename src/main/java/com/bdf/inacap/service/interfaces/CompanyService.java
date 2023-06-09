package com.bdf.inacap.service.interfaces;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    public List<CompanyDTO> getAll ();

    public CompanyDTO save (CompanyDTO newCompany);

    public CompanyDTO deleteByID (Long id);

    public CompanyDTO updateByID (CompanyDTO newCompany, Long id);

    public CompanyDTO getCompanyByID(Long id);

    public CompanyDTO getCompanyByCuit(Long cuit);
}
