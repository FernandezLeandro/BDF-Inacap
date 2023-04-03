package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class PlanDePagoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(nullable = true)
    private Long idPlanDePago;

    @ManyToOne
    private Acta acta;

    @ManyToOne
    private BoletaEmitida boleta;

    @ManyToOne
    private Periodo periodo;

    @Basic
    private Double periodoValor;

    @Basic
    private Integer empleadosAbonado;

    @Basic
    private Double importeAbonado;

    @Basic
    private Double interesesAbonado;

    @Basic
    private Integer empleadosAuditado;

    @Basic
    private Double nominalAPagar;

    @Basic
    private Double intereses;

    @Basic
    private Date fechaCalcIntereses;

    @Basic
    private Date fechaPago;

    @ManyToOne
    private Cheque cheque;


    @Basic
    @Column(nullable = false)
    private Boolean borrado;

    @Column(nullable = false, length = 50)
    private String usuarioUltimaModif;

    @Basic
    @Column(nullable = false)
    private Calendar fechaUltimaModif;

}
