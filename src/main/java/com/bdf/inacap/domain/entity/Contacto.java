package com.bdf.inacap.domain.entity;


import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Embeddable
public class Contacto {

    @Basic
    String nombre;

    @Basic
    String apellido;

    @Basic
    String mail;

    @Basic
    String telefono;


}
