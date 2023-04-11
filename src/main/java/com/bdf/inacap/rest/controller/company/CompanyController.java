package com.bdf.inacap.rest.controller.company;

import com.bdf.inacap.domain.mapper.CompanyMapper;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;
import com.bdf.inacap.service.interfaces.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController implements CompanyAPI{

    private CompanyService companyService;


    @Autowired
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }
    @Override
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll() {
        return ResponseEntity.ok(this.companyService.getAll());
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CompanyDTO> getById(Long id) {
        return ResponseEntity.ok(this.companyService.getCompanyByID(id));
    }

    @Override
    @GetMapping("/cuit/{cuit}")
    public ResponseEntity<CompanyDTO> getByCuit(Long cuit) {
        return ResponseEntity.ok(this.companyService.getCompanyByCuit(cuit));
    }

    @Override
    @PostMapping
    public ResponseEntity<CompanyDTO> create(@Valid CompanyDTO companyDTO) {
       return ResponseEntity.ok(this.companyService.save(companyDTO));
    }



    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyDTO> deleteByID(Long id) {
        return ResponseEntity.ok(this.companyService.deleteByID(id));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateByID(CompanyDTO companyDTO, Long id) {
        return ResponseEntity.ok(this.companyService.updateByID(companyDTO,id));
    }
}
