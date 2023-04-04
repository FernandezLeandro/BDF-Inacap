package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ChequeEnCarteraLog")
public class ChequeEnCarteraLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chequeEnCarteraId", nullable = false)
    private ChequeEnCartera chequeEnCarteraId;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date fecha;

    @Basic
    protected String usuario;

    @OneToOne
    private Cheque cheque;

    // Banco de origen del cheque.
    @ManyToOne
    private Cuenta bancoPago;

    // Banco donde se deposita el cheque.
    @ManyToOne
    private Banco bancoDeposito;

    @Basic
    private String nroCheque;

    // Fecha en la que el cheque es recibido en la cartera.
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcion;

    // Fecha en la que se emitió el cheque. Dato del cheque físico.
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    // Fecha en la que se acredita el cheque. Dato del cheque físico.
    @Temporal(TemporalType.DATE)
    private Date fechaAcreditacion;

    // Fecha en la que se depositó el cheque.
    @Temporal(TemporalType.DATE)
    private Date fechaDeposito;

    // Fecha en la que se confirmó como aceptado o rechazado el cheque.
    @Temporal(TemporalType.DATE)
    private Date fechaConfirmacion;

    @Enumerated
    private EstadoCheque estado;

    // Detalle el algún cambio.
    @Basic
    private String observaciones;

    // Estado con el que proviene del acta. Si hubo cambios o no.
    @Basic
    private Observacion estadoObservacion;

    @Basic
    private BigDecimal importe;

    // Empresa a la que corresponde el cheque.
    @ManyToOne
    private Empresa empresa;

    // Cheque al que reemplaza.
    @OneToOne
    private ChequeEnCartera reemplazado;

    @Basic
    @Column(nullable = false)
    private Boolean borrado;

    public ChequeEnCarteraLog(ChequeEnCartera chequeEnCartera, String login) {
        this.chequeEnCarteraId = chequeEnCartera;
        this.fecha = Calendar.getInstance().getTime();
        this.usuario = login;
        this.cheque = chequeEnCartera.getCheque();
        this.bancoDeposito = chequeEnCartera.getBancoDeposito();
        this.bancoPago = chequeEnCartera.getBancoPago();
        this.borrado = chequeEnCartera.getBorrado();
        this.empresa = chequeEnCartera.getEmpresa();
        this.estado = chequeEnCartera.getEstado();
        this.estadoObservacion = chequeEnCartera.getEstadoObservacion();
        this.fechaAcreditacion = chequeEnCartera.getFechaAcreditacion();
        this.fechaConfirmacion = chequeEnCartera.getFechaConfirmacion();
        this.fechaDeposito = chequeEnCartera.getFechaDeposito();
        this.fechaEmision = chequeEnCartera.getFechaEmision();
        this.fechaRecepcion = chequeEnCartera.getFechaRecepcion();
        this.importe = chequeEnCartera.getImporte();
        this.nroCheque = chequeEnCartera.getNroCheque();
        this.observaciones = chequeEnCartera.getObservaciones();
        this.reemplazado = chequeEnCartera.getReemplazado();
    }

}