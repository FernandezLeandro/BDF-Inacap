package com.bdf.inacap.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
public class Provincia {
    @Basic
    @Column(unique = true, nullable = false, length = 50,name="nombre")
    private String nombre;


    public Provincia(Long id) {
        super();
        this.id = id;
    }

    public Provincia(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }
}
