package com.bdf.inacap.service.impl;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.exception.BadRequestException;
import com.bdf.inacap.exception.CodeError;
import com.bdf.inacap.exception.CompanyException;
import com.bdf.inacap.repository.CompanyRepository;
import com.bdf.inacap.service.interfaces.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl (CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDE> getAll() {
        return getCompaniesEnabled();
    }



    @Override
    public CompanyDE add(CompanyDE newCompany){
        if(newCompany.getName()!=null){
            newCompany.setEstadoAlta(true);
            return this.companyRepository.save(newCompany);
        }
        throw new BadRequestException("badrequest");
    }

    @Override
    public CompanyDE deleteByID(Long id) {
        CompanyDE companyToDelete =  this.getCompanyByID(id);
        if (!isCompanyNull(companyToDelete)) {
            companyToDelete.setEstadoAlta(false);
            //this.companyRepository.save(companyToDelete);
            this.companyRepository.delete(companyToDelete);
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

    private List<CompanyDE> getCompaniesEnabled() {
        log.info("Es: " + this.companyRepository.findAll().size());
        if(this.companyRepository.findAll().isEmpty()){
            log.info("Vacio");
            throw new CompanyException(HttpStatus.NO_CONTENT, "Companies is empty", CodeError.C204);
        }
        return this.companyRepository.findAll().stream()
                .filter(company -> company.getEstadoAlta())
                .collect(Collectors.toList());
    }

}
