package com.bdf.inacap.service;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.repository.CompanyRepository;
import com.bdf.inacap.service.impl.CompanyServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
    private EasyRandom generator;

    @BeforeEach
    public void setUp (){
        this.generator = new EasyRandom();
        this.company = generator.nextObject(CompanyDE.class);

//        this.company = new CompanyDE();
//        this.company.id = 1L;
//        this.company.name = "BDF";
//        this.company.estadoAlta = true;
    }

    @Test
    public void shouldReturnAllCompanies() {
        this.company.estadoAlta = true;

        when(this.companyRepository.findAll()).thenReturn(Arrays.asList(company));

        assertEquals(this.companyService.getAll(), Arrays.asList(company));
    }

    @Test
    public void shouldFailToReturnAllCompanies() {
        this.company.estadoAlta= false;
        CompanyDE [] companiesEmpty = {};

        when(this.companyRepository.findAll()).thenReturn(Arrays.asList(company));

        assertEquals(this.companyService.getAll(),List.of(companiesEmpty));
    }

}
