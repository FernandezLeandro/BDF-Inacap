package com.bdf.inacap.service.impl;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.domain.mapper.CompanyMapper;
import com.bdf.inacap.exception.CodeError;
import com.bdf.inacap.exception.CompanyException;
import com.bdf.inacap.repository.CompanyRepository;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;
import com.bdf.inacap.service.interfaces.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public List<CompanyDTO> getAll() {
        return getCompaniesEnabled();
    }

    private List<CompanyDTO> getCompaniesEnabled() {
        if (this.companyRepository.findAll().isEmpty()) {
            throw new CompanyException(HttpStatus.NO_CONTENT, "Companies is empty", CodeError.C204);
        }
        return this.companyRepository.findAll().stream()
                .filter(company -> company.getEstadoAlta())
                .map(this.companyMapper::deToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO save(CompanyDTO newCompany) {
        CompanyDE companyDE = this.companyMapper.dtoToDE(newCompany);
        if (newCompany.getNameCompany() == null || newCompany.getNameCompany() == "") {
            throw new CompanyException(HttpStatus.BAD_REQUEST, "Name is null", CodeError.C400);
        }
        companyDE.setEstadoAlta(true);
        return this.companyMapper.deToDTO(this.companyRepository.save(companyDE));

    }

    @Override
    public CompanyDTO deleteByID(Long id) {
        /* OLD CODE
        CompanyDE companyDE = this.companyMapper.dtoToDE(this.getCompanyByID(id));
        companyDE.setEstadoAlta(false);
        this.companyRepository.save(companyDE);
         */
        if(findCompanyById(id).isPresent()){
            CompanyDE companyDE = findCompanyById(id).get();
            companyDE.setEstadoAlta(false);
            return this.companyMapper.deToDTO(this.companyRepository.save(companyDE));
        }else
            throw new CompanyException(HttpStatus.NOT_FOUND, "Id doesn't find", CodeError.C404);
    }
    //TODO: Creo que seria una mejora, debido a que podemos filtrar directamente sobre los registros activos (estadoAlta = true)
    private Optional<CompanyDE> findCompanyById(Long id) {
        return this.findCompaniesEnabled().stream().filter(companyDE -> Objects.equals(companyDE.getId(), id)).findFirst();
    }
    //TODO: Comprobar si esta bien, podria utilizar este metodo en getAll y en ese metodo realizar el mapeo? Asi tengo findCompaniesEnabled (retorna DEs)
    // y getCompaniesEnabled (retorna DTOs)
    private List<CompanyDE> findCompaniesEnabled (){
        return this.companyRepository.findAll().stream()
                .filter(CompanyDE::getEstadoAlta)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO updateByID(CompanyDTO newCompany, Long id) {
        CompanyDE companyDE = this.companyMapper.dtoToDE(this.getCompanyByID(id));
        companyDE.setContactName(newCompany.contactName);
        companyDE.setEstadoAlta(true);
        return this.companyMapper.deToDTO(this.companyRepository.save(companyDE));
    }



    public CompanyDTO getCompanyByID(Long id) {
        this.companyRepository.findById(id).orElseThrow(
                () -> new CompanyException(HttpStatus.NOT_FOUND, "Id doesn't find", CodeError.C404));
        return this.companyRepository.findById(id)
                .map(this.companyMapper::deToDTO)
                .orElse(null);
    }

    @Override
    public CompanyDTO getCompanyByCuit(Long cuit) {
        CompanyDE companyDE = this.companyRepository.findByCuit(cuit);
        if (companyDE == null) {
            throw new CompanyException(HttpStatus.NOT_FOUND, "Cuit doesn't find", CodeError.C404);
        }
        return this.companyMapper.deToDTO(this.companyRepository.findByCuit(cuit));
    }

    private boolean isCompanyNull(CompanyDE companyDE) {
        return companyDE == null;
    }

}
