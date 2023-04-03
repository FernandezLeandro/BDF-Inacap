package com.bdf.inacap.domain.entity;

import com.bdf.inacap.utils.FormateadorDeFechas;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Entity
@Data
public class Acta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @Temporal(TemporalType.DATE)
    private Date fechaCierre;

    @Temporal(TemporalType.DATE)
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

    @ManyToOne
    private Usuario aprobadorCantidadPagos;

    @Basic
    private Double diferencias;

    @Transient
    private String cuotaDesde;

    @Transient
    private String cuotaHasta;

    @OneToMany(mappedBy = "acta")
    List<PlanDePago> planes;

//    @OneToMany(mappedBy="acta")
//    @Fetch(FetchMode.SUBSELECT)
//    List<ModificacionActa> modificaciones;

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

    @Basic
    @Column(nullable = false)
    private Boolean tieneInteresFinanciacion;

    @Basic
    @Column(nullable = false)
    private Long secuencia;

    //TODO: verificar si es necesario
    public Acta() {
        super();
        this.secuencia = 0L;
        this.contacto_apellido = "";
        this.contacto_nombre = "";
        this.contacto_mail = "";
        this.contacto_telefono = "";
        this.planes = new ArrayList<PlanDePago>();
        this.modificaciones = new ArrayList<ModificacionActa>();
        this.observacionAprobador = "";
        this.observaciones = "";
        this.borrado = false;
        this.tieneInteresFinanciacion = true;
    }

    @OneToMany(mappedBy = "acta")
    @Fetch(FetchMode.SUBSELECT)
    List<ModificacionActa> modificaciones;

    @Transient
    private boolean fueEditada;

    public String getFechaAltaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (this.fechaAlta != null) {
            return sdf.format(this.fechaAlta);
        }
        return "";
    }

    public String getFechaCierreString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (this.fechaCierre != null) {
            return sdf.format(this.fechaCierre);
        }
        return "";
    }


    public String getFechaAprobacionString(String language) {
        DateFormat sdf = FormateadorDeFechas.getDateFormater(language);
        if (this.fechaAprobacion != null) {
            return sdf.format(this.fechaAprobacion);
        }
        return "";
    }

    public String getFechaAprobacionString() {
        DateFormat sdf = FormateadorDeFechas.getDateFormater("");
        if (this.fechaAprobacion != null) {
            return sdf.format(this.fechaAprobacion);
        }
        return "";
    }


    public String getCuotaDesde() throws Exception {
        Periodo menor = new Periodo();
        menor.setPeriodo("");
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 1, 1);
        menor.setVencimiento(cal);

        for (Iterator iterator = this.getPlanes().iterator(); iterator.hasNext(); ) {
            PlanDePago type = (PlanDePago) iterator.next();
            if (type.getPeriodo().getVencimiento().before(menor.getVencimiento()))
                menor = type.getPeriodo();
        }
        return menor.getPeriodo();
    }

    public String getCuotaHasta() {
        Periodo mayor = new Periodo();
        mayor.setPeriodo("");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 1, 1);
        mayor.setVencimiento(cal);
        for (Iterator iterator = this.getPlanes().iterator(); iterator.hasNext(); ) {
            PlanDePago type = (PlanDePago) iterator.next();
            if (type.getPeriodo().getVencimiento().after(mayor.getVencimiento()))
                mayor = type.getPeriodo();
        }
        return mayor.getPeriodo();
    }

    public void logCambio(ModificacionActa cambio) {
        if (getModificaciones() == null)
            setModificaciones(new ArrayList<ModificacionActa>());
        modificaciones.add(cambio);
    }

}
