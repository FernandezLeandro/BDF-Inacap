package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    String nombre;

    @Basic
    String apellido;

    @Basic
    String mail;

    @Basic
    String telefono;


}
