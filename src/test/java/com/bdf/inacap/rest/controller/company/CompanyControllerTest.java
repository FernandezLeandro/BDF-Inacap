package com.bdf.inacap.rest.controller.company;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.domain.mapper.CompanyMapper;
import com.bdf.inacap.exception.ControllerAdvice;
import com.bdf.inacap.repository.CompanyRepository;
import com.bdf.inacap.service.impl.CompanyServiceImpl;
import com.bdf.inacap.service.interfaces.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class CompanyControllerTest {
    @Mock
    private CompanyRepository companyRepository;
    @Autowired
    MockMvc mockMvc;

    private EasyRandom generator;
    private CompanyDE company;
    @InjectMocks
    private CompanyServiceImpl companyService;
    @Autowired
    private CompanyMapper companyMapper;

    @BeforeEach
    public void setUp (){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new CompanyControllerImpl(companyService, companyMapper))
                .setControllerAdvice(new ControllerAdvice())
                .build();
        this.generator = new EasyRandom();
        this.company = generator.nextObject(CompanyDE.class);

    }

    @Test
    public void shouldGetAllUniversesIs200() throws Exception {
        this.company.estadoAlta = true;
        List <CompanyDE> list= Arrays.asList(company);
        when(this.companyService.getAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk());
    }
}
