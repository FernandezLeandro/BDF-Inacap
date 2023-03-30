package com.bdf.inacap.service.interfaces;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    public List<CompanyDE> getAll ();

    public CompanyDE add (CompanyDE newCompany);

    public CompanyDE deleteByID (Long id);

    public CompanyDE updateByID (CompanyDE newCompany, Long id);

    public CompanyDE getCompanyByID(Long id);

    public CompanyDE getCompanyByCuit(Long cuit);
}
