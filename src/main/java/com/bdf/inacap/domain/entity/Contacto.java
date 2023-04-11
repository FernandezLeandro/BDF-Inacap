package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Contacto {

    String nombre;

    String apellido;

    String mail;

    String telefono;


}
