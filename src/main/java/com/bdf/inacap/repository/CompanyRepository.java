package com.bdf.inacap.repository;

import com.bdf.inacap.domain.entity.CompanyDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDE, Long> {
    public CompanyDE findByCuit(Long cuit);
}
