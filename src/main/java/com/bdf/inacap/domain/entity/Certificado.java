package com.bdf.inacap.domain.entity;

import lombok.Data;

import java.util.Calendar;
import jakarta.persistence.*;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Certificado")
public class Certificado {

    @ManyToOne(fetch=FetchType.EAGER)
    Acta acta;

    @ManyToOne(fetch=FetchType.EAGER)
    Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar fecha;

    public Certificado() {
        fecha = Calendar.getInstance();
    }

    public Certificado(Usuario usuario, Acta acta){
        this();
        this.usuario = usuario;
        this.acta = acta;
    }


}
