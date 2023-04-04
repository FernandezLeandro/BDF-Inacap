package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

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
