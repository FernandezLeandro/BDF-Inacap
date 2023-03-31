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

        //this.companyRepository.findByCuit(newCompany.cuit).orElseThrow(
        //        () -> new CompanyException(HttpStatus.BAD_REQUEST, "Company already exists", CodeError.C400));

        if(newCompany.getName()==null || newCompany.getName()=="" ){
            throw new CompanyException(HttpStatus.BAD_REQUEST, "Name is null", CodeError.C400);
        }
        newCompany.setEstadoAlta(true);
        return this.companyRepository.save(newCompany);

    }

    @Override
    public CompanyDE deleteByID(Long id) {
        CompanyDE companyToDelete =  this.getCompanyByID(id);
        if (!isCompanyNull(companyToDelete)) {
            companyToDelete.setEstadoAlta(false);
            this.companyRepository.save(companyToDelete);
            //this.companyRepository.delete(companyToDelete);
        }
        return companyToDelete; // Agregar excepciones o response entity personalizado (iria en service?)
    }

    @Override
    public CompanyDE updateByID(CompanyDE newCompany, Long id) {
        CompanyDE companyToUpdate = this.getCompanyByID(id);
        companyToUpdate.setContactName(newCompany.contactName);
        return this.companyRepository.save(companyToUpdate);
    }

    public CompanyDE getCompanyByID(Long id) {
        return this.companyRepository.findById(id).orElseThrow(
                () -> new CompanyException(HttpStatus.NOT_FOUND, "Id doesn't find", CodeError.C404));
    }

    @Override
    public CompanyDE getCompanyByCuit(Long cuit) {
        return this.companyRepository.findByCuit(cuit).orElseThrow(
                () -> new CompanyException(HttpStatus.NOT_FOUND, "Cuit doesn't find", CodeError.C404));
    }

    private boolean isCompanyNull (CompanyDE companyDE){
        return companyDE == null;
    }

    private List<CompanyDE> getCompaniesEnabled() {
        if(this.companyRepository.findAll().isEmpty()){
            throw new CompanyException(HttpStatus.NO_CONTENT, "Companies is empty", CodeError.C204);
        }
        return this.companyRepository.findAll().stream()
                .filter(company -> company.getEstadoAlta())
                .collect(Collectors.toList());
    }

}
