package com.bdf.inacap.service;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.repository.CompanyRepository;
import com.bdf.inacap.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private CompanyDE company;
    @BeforeEach
    public void setUp (){
        this.company = new CompanyDE();
        this.company.id = 1L;
        this.company.name = "BDF";
        this.company.estadoAlta = true;
    }

    @Test
    public void shouldReturnAllCompanies() {
        when(this.companyRepository.findAll()).thenReturn(Arrays.asList(company));

        assertEquals(this.companyService.getAll(), Arrays.asList(company));
    }

    @Test
    public void shouldFailToReturnAllCompanies() {
        this.company.estadoAlta= false;
        CompanyDE [] companies = {};
        when(this.companyRepository.findAll()).thenReturn(Arrays.asList(company));
        assertEquals(this.companyService.getAll(),List.of(companies));
    }

}
