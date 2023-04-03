package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Domicilio {

    @Column(nullable = false ,length = 30,name="calle")
    private String calle;

    @Column(nullable = false , length = 8,name="numero")
    private Long numero;

    @Column(length = 2,name="piso")
    private Long piso;

    @Column(length = 5,name="dpto")
    private String dpto;

    @Column(nullable = false, length = 8)
    private String codigoPostal;

    @Column(nullable = false, length = 30)
    private String localidad;

    @ManyToOne
    private Provincia provincia;



    @Transient
    public String codigoPostalMasLocalidad() {
        return codigoPostal + " " + localidad;
    }

    public String toStringShort() {
        String retorno = "";
        if (this.getCalle() != null)
            retorno += this.getCalle() + " ";

        if (this.getNumero() != null)
            retorno += this.getNumero() + " ";

        if ((this.getPiso() != null) && (this.getPiso()!=0L)) {
            retorno += " Piso " + this.getPiso() + " ";

            if (this.getDpto() != null)
                retorno += " Depto " + this.getDpto() + " ";
        }

        return retorno;
    }

    public String toString() {
        String retorno = "";
        if (this.getCalle() != null)
            retorno += this.getCalle();
        retorno+= ",";
        if (this.getNumero() != null)
            retorno += this.getNumero();
        retorno+= ",";
        if ((this.getPiso() != null))
            retorno += this.getPiso();
        retorno+= ",";
        if (this.getDpto() != null)
            retorno += this.getDpto();
        retorno+= ",";
        if(this.getLocalidad()!=null)
            retorno += localidad;
        retorno+= ",";
        if(this.getProvincia()!=null)
            retorno +=provincia.getNombre();
        retorno+= ",";
        if(this.getCodigoPostal()!=null)
            retorno +=codigoPostal;
        return retorno;
    }
    
    public String getCodigoPostalLocalidad() {
        return codigoPostal + " - " + localidad;
    }
    
    public void setCodigoPostalLocalidad(String codigoPostalLocalidad){
        String[] strings = codigoPostalLocalidad.split(" - ");
        this.codigoPostal=strings[0];
        this.localidad=strings[1];

    }

}
