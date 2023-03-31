package com.bdf.inacap.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Data
public class ZipCode{

    @Column(nullable = false, length= 100,name="codigopostal")
    private String codigoPostal;

    @Column(nullable = false, length= 100,name="partido")
    private String partido;

    @Column(nullable = false, length= 100,name="localidad")
    private String localidad;

    @ManyToOne
    private Provincia provincia;

    @Transient
    private String localidadCompuesta;

    public Long getId_provincia() {
        if(provincia != null && provincia.getId() != null){
            return provincia.getId();
        }else {
            return -1L;
        }
    }

    public String getLocalidadCompuesta(){
        this.localidadCompuesta = this.codigoPostal + " - " + this.partido + " - " + this.localidad;
        return this.localidadCompuesta;
    }

    public static String getOptionsList(List<ZipCode> codigosPostales){
        StringBuilder ciudadesString=new StringBuilder("<option value='-1' label='Seleccionar'/>");
        for (ZipCode codigoPostal : codigosPostales) {
            ciudadesString.append("<option value='"+codigoPostal.getCodigoPostal()+"'>"+codigoPostal.getLocalidadCompuesta()+"</option>");
        }
        return ciudadesString.toString();
    }

    public String getCodigoPostalLocalidad(){
        return codigoPostal + " - " + localidad;
    }
}
