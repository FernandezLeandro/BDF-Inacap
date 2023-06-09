package com.bdf.inacap.rest.controller.company;


import com.bdf.inacap.domain.entity.CompanyDE;
import com.bdf.inacap.rest.controller.dto.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CompanyAPI {

    public ResponseEntity<List<CompanyDTO>> getAll ();

    public ResponseEntity<CompanyDTO> getById(@PathVariable Long id);

    public ResponseEntity<CompanyDTO> getByCuit(@PathVariable Long cuit);

    public ResponseEntity<CompanyDTO> create (@RequestBody CompanyDTO companyDTO);

    public ResponseEntity<CompanyDTO> deleteByID (@PathVariable Long id);

    public ResponseEntity<CompanyDTO> updateByID (@RequestBody CompanyDTO companyDTO, @PathVariable Long id);

}
