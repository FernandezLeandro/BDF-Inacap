package com.bdf.inacap.rest.controller.company;

import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.domain.mapper.CompanyMapper;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;
import com.bdf.inacap.service.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyControllerImpl implements CompanyController{

    private CompanyService companyService;
    private CompanyMapper companyMapper;

    @Autowired
    public CompanyControllerImpl (CompanyService companyService, CompanyMapper companyMapper){
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }
    @Override
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll() {
        return ResponseEntity.ok(
                this.companyService.getAll().stream()
                        .map(this.companyMapper::deToDTO)
                        .collect(Collectors.toList())
                );
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CompanyDTO> getById(Long id) {
        return ResponseEntity.ok(
                this.companyMapper.deToDTO(
                this.companyService.getCompanyByID(id)));
    }

    @Override
    @GetMapping("/cuit/{cuit}")
    public ResponseEntity<CompanyDTO> getByCuit(Long cuit) {
        return ResponseEntity.ok(this.companyMapper.deToDTO(this.companyService.getCompanyByCuit(cuit)));
    }

    @Override
    @PostMapping
    public ResponseEntity<CompanyDTO> add(CompanyDTO companyDTO) {
        return ResponseEntity.ok(
                this.companyMapper.deToDTO(
                this.companyService.add(this.companyMapper.dtoToDE(companyDTO))
                ));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyDTO> deleteByID(Long id) {
        return ResponseEntity.ok(
                this.companyMapper.deToDTO(this.companyService.deleteByID(id))
        );
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateByID(CompanyDTO companyDTO, Long id) {
        return ResponseEntity.ok(
                this.companyMapper.deToDTO(this.companyService.updateByID(
                        this.companyMapper.dtoToDE(companyDTO),id)));
    }
}
