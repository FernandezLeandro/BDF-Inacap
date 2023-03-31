package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;



@SuppressWarnings("serial")
@Data
@Entity
public class EmpresaContador{

    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.EAGER)
    private Contador contador;

    private String clave;

    @Temporal(TemporalType.DATE)
    private Calendar fechaAsignacion;

    private String responsableCliente;

    private String mailResponsableCliente;

    @Transient
    private String claveActual;

}