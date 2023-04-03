package com.bdf.inacap.domain.entity;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Banco")
public class Banco{

    @Basic
    @Column(unique = true, nullable = false)
    private String nombre;

}
