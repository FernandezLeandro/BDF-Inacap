package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;



@SuppressWarnings("serial")
@Data
@Entity
@Table(name="DebitoAutomaticoLog")
public class DebitoAutomaticoLog {

    @ManyToOne
    private DebitoAutomatico debitoAutomatico;

    @Temporal(TemporalType.DATE)
    private Date fechaAcreditacion;

    @ManyToOne
    private Banco banco;


    @Basic
    private String cbu;

    //Fecha en la que el Banco informa que pudo cobrar
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcion;

    @Basic
    private Double importe;

    @Enumerated
    @Column(nullable=false)
    private EstadoDebitoAutomatico estado;

    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @Basic
    @Column(nullable = false)
    private String usuario;


}
