package com.bdf.inacap.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
public class EstadoItemLegal extends Auditable{
    //Id no lo tenia, por eso el suppressWarnings?
    @Id
    Long id;
    @Basic
    @Column(nullable = false, unique = true)
    String nombre;

    @Basic
    @Column(nullable = false)
    String descripcion;

}