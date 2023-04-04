package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, name = "codigopostal")
    private String codigoPostal;

    @Column(nullable = false, length = 100, name = "partido")
    private String partido;

    @Column(nullable = false, length = 100, name = "localidad")
    private String localidad;

    @ManyToOne
    private Provincia provincia;

    @Transient
    private String localidadCompuesta;

    public Long getId_provincia() {
        if (provincia != null && provincia.getId() != null) {
            return provincia.getId();
        } else {
            return -1L;
        }
    }

    public String getLocalidadCompuesta() {
        this.localidadCompuesta = this.codigoPostal + " - " + this.partido + " - " + this.localidad;
        return this.localidadCompuesta;
    }

    public static String getOptionsList(List<ZipCode> codigosPostales) {
        StringBuilder ciudadesString = new StringBuilder("<option value='-1' label='Seleccionar'/>");
        for (ZipCode codigoPostal : codigosPostales) {
            ciudadesString.append("<option value='" + codigoPostal.getCodigoPostal() + "'>" + codigoPostal.getLocalidadCompuesta() + "</option>");
        }
        return ciudadesString.toString();
    }

    public String getCodigoPostalLocalidad() {
        return codigoPostal + " - " + localidad;
    }
}
