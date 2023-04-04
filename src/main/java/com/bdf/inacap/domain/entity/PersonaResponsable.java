package com.bdf.inacap.domain.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonaResponsable {

    @Basic
    @Column(nullable = false, length = 100)
    private String nombre;

    @Basic
    @Column(nullable = false, name = "mailResponsable", length = 70)
    private String mail;

    @Basic
    @Column(nullable = false, name = "nroTelefonoResponsable", length = 20)
    private String nroTelefono;

}

