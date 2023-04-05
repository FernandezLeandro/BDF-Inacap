package com.bdf.inacap.service;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.domain.mapper.CompanyMapper;
import com.bdf.inacap.exception.BadRequestException;
import com.bdf.inacap.exception.CompanyException;
import com.bdf.inacap.repository.CompanyRepository;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;
import com.bdf.inacap.service.impl.CompanyServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyMapper companyMapper;
    @InjectMocks
    private CompanyServiceImpl companyService;

    private CompanyDE company;
    private CompanyDTO companyDTO;
    private EasyRandom generator;
    private List<CompanyDTO> emptyCompaniesDTO;
    private List<CompanyDE> emptyCompaniesDE;

    @BeforeEach
    public void setUp (){
        this.generator = new EasyRandom();
        this.company = generator.nextObject(CompanyDE.class);
        this.companyDTO = new CompanyDTO();
        this.companyDTO.setNameCompany(this.company.getName());
        this.companyDTO.setContactName(this.company.getContactName());
        this.companyDTO.setBussinesName(this.company.getBussinesName());
        this.companyDTO.setCuit(this.company.getCuit().toString());
        this.companyDTO.setEmail(this.company.getEmail());
        this.companyDTO.setContactNumber(this.company.getContactNumber());
        this.companyDTO.setId(this.company.getId());
//        this.company = new CompanyDE();
//        this.company.id = 1L;
//        this.company.name = "BDF";
//        this.company.estadoAlta = true;
    }

    @Test
    @DisplayName("Deberia retornar todas las companias activas")
    public void shouldReturnAllCompanies() {
        this.company.estadoAlta = true;
        when(this.companyRepository.findAll()).thenReturn(Arrays.asList(company));
        when(this.companyMapper.deToDTO(any(CompanyDE.class))).thenReturn(companyDTO);


        assertEquals(this.companyService.getAll(), Arrays.asList(companyDTO));
        assertNotNull(this.companyService.getAll());

    }

    @Test
    @DisplayName("Deberia retornar una lista vacia cuando sus elementos tienen estadoAlta=false")
    public void shouldReturnEmptyCompanies() {
        this.company.estadoAlta= false;
        initEmptyCompaniesDTO();

        when(this.companyRepository.findAll()).thenReturn(Arrays.asList(company));

        assertEquals(this.companyService.getAll(),this.emptyCompaniesDTO);
        assertNotNull(this.companyService.getAll());
    }
    //TODO: Se puede abstraer los dos metodos initEmpty... en uno
    private void initEmptyCompaniesDTO() {
        this.emptyCompaniesDTO = new ArrayList<>();
    }
    private void initEmptyCompaniesDE() {
        this.emptyCompaniesDE= new ArrayList<>();
    }

    @Test
    @DisplayName("Deberia tirar CompanyException si la lista no tiene elementos")
    public void shouldFailCompanyExceptionToReturnAllCompanies() {
        initEmptyCompaniesDE();
        when(this.companyRepository.findAll()).thenReturn(this.emptyCompaniesDE);

        assertThrows(CompanyException.class, () -> this.companyService.getAll());
    }
/*
    @Test
    public void shouldReturnCompanyByCuit() {
        when(this.companyRepository.findByCuit(any(Long.class))).thenReturn(Optional.ofNullable(this.company));

        assertEquals(this.companyService.getCompanyByCuit(this.company.getCuit()), this.company);
    }

    @Test
    public void shouldFailCompanyException() {
        initEmptyCompanies();

        when(this.companyRepository.findAll()).thenReturn(this.emptyCompanies);

        assertThrows(CompanyException.class, () -> this.companyService.getAll());
    }

    @Test
    public void saveCompany() {
        when(this.companyRepository.save(this.company)).thenReturn(this.company);
        assertNotNull(this.companyService.add(this.company));
    }

    @Test
    public void saveCompanyNameNullException() {
        this.company.name =null;
        assertThrows(CompanyException.class,()-> this.companyService.add(this.company));
    }

    @Test
    public void shouldReturnDeleteCompany() {
        this.company.estadoAlta= false;
        when(this.companyRepository.findById(company.id)).thenReturn(Optional.ofNullable(this.company));
        when(this.companyRepository.save(this.company)).thenReturn(this.company);
        assertFalse(this.companyService.deleteByID(this.company.id).getEstadoAlta());
    }


*/
}
