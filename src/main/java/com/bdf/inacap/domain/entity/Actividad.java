package com.bdf.inacap.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Actividad")
public class Actividad{

    @Column(unique = true, nullable = false,name="actividad")
    private String actividad;


}
