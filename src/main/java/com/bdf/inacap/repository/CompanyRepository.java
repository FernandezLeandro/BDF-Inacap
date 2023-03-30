package com.bdf.inacap.repository;

import com.bdf.inacap.domain.entity.CompanyDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDE, Long> {
    public Optional<CompanyDE> findByCuit(Long cuit);
}
