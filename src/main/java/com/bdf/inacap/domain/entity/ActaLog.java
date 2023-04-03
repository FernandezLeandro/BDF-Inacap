package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Calendar;
import java.util.Date;


@Entity
@Data
public class ActaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(nullable = true)
    private Long idActa;

    @ManyToOne
    private Empresa empresa;

    @Basic
    private String contacto_nombre;

    @Basic
    private String contacto_apellido;

    @Basic
    private String contacto_mail;

    @Basic
    private String contacto_telefono;

    @Enumerated
    private EstadoActa estado;

    @Basic
    private Date fechaAlta;

    @Basic
    private Date fechaCierre;

    @Basic
    private Date fechaAprobacion;

    @ManyToOne
    private Usuario fiscal;

    @ManyToOne
    private Usuario aprobadorIntereses;

    @ManyToOne
    private Usuario aprobadorFecha;

    @ManyToOne
    private Usuario aprobadorFechaAcreditacion;

    @ManyToOne
    private Usuario aprobadorActa;

    @Basic
    private Double diferencias;

    @Basic
    private String observaciones;

    @Basic
    private String observacionAprobador;

    @ManyToOne
    private Usuario actaCreador;

    @ManyToOne
    private Usuario actaCierre;

    @Basic
    @Column(nullable = false)
    private Boolean borrado;

    @Column(nullable = false, length = 50)
    private String usuarioUltimaModif;

    @Basic
    @Column(nullable = false)
    private Calendar fechaUltimaModif;


}