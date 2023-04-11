package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ChequeRecibo")
public class ChequeRecibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long nroRecibo;

    @ManyToOne
    private Acta acta;

    private String banco;

    private String nroCheque;

    @Temporal(TemporalType.DATE)
    private Date fechaAcreditacion;

    @Enumerated
    private EstadoChequeRecibo estado;

    private String observaciones;

    private Double importe;

    @Column(nullable = false)
    private Boolean borrado;

    @ManyToOne
    private Usuario usuarioAlta;

    @Temporal(TemporalType.DATE)
    private Date fechaAlta;


}