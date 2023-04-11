package com.bdf.inacap.domain.entity;

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

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, name = "mailResponsable", length = 70)
    private String mail;

    @Column(nullable = false, name = "nroTelefonoResponsable", length = 20)
    private String nroTelefono;

}

