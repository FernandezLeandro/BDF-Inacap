package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "ChequeRecibo")
public class ChequeRecibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Long nroRecibo;

    @ManyToOne
    private Acta acta;

    @Basic
    private String banco;

    @Basic
    private String nroCheque;

    @Temporal(TemporalType.DATE)
    private Date fechaAcreditacion;

    @Enumerated
    private EstadoChequeRecibo estado;

    @Basic
    private String observaciones;

    @Basic
    private Double importe;

    @Basic
    @Column(nullable = false)
    private Boolean borrado;

    @ManyToOne
    private Usuario usuarioAlta;

    @Temporal(TemporalType.DATE)
    private Date fechaAlta;


}