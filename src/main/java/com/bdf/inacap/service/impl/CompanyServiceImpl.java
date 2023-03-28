package com.bdf.inacap.service.impl;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.repository.CompanyRepository;
import com.bdf.inacap.service.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl (CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
    @Override
    public List<CompanyDE> getAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public CompanyDE add(CompanyDE newCompany) {
        return this.companyRepository.save(newCompany);
    }

    @Override
    public CompanyDE deleteByID(Long id) {
        CompanyDE companyToDelete =  this.getCompanyByID(id);
        if (!isCompanyNull(companyToDelete)) {
            this.companyRepository.delete(companyToDelete);
            //return this.companyMapper.deToDto(companyToDelete);
        }
        return companyToDelete; // Agregar excepciones o response entity personalizado (iria en service?)
    }

    @Override
    public CompanyDE updateByID(CompanyDE newCompany, Long id) {
        return null;
    }


    private CompanyDE getCompanyByID(Long id) {
        Optional<CompanyDE> company = this.companyRepository.findById(id);
        return company.isPresent() ? company.get() : null;
    }

    @Override
    public CompanyDE getCompanyByCuit(Long cuit) {
        return this.companyRepository.findByCuit(cuit);
    }

    private boolean isCompanyNull (CompanyDE companyDE){
        return companyDE == null;
    }
}
