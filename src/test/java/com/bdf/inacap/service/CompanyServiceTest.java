package com.bdf.inacap.service;

import com.bdf.inacap.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CompanyServiceTest {

    private CompanyRepository heroRepository;

    @BeforeEach
    public void setUp (){
        MockitoAnnotations.initMocks(this);
    }

}
