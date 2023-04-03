package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Data
@Entity(name = "DebitoAutomatico")
public class DebitoAutomatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DebitoAutomatico() {
        super();
        this.historial = new ArrayList<DebitoAutomaticoLog>();
        setEstado(EstadoDebitoAutomatico.PENDIENTE);
        setBorrado(false);
        setContadorEnvios(0);
    }

    private transient Date fechaDePago;        // Solo para
    // eliminar Débito

    @Temporal(TemporalType.DATE)
    private Date fechaAcreditacion;

    @ManyToOne
    private Banco banco;

    @Basic
    private String cbu;

    // Fecha en la que el Banco informa que pudo cobrar
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcion;

    @Basic
    private String observaciones;

    @Basic
    private Double importe;

    @Enumerated
    @Column(nullable = false)
    private EstadoDebitoAutomatico estado;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "debitoAutomatico")
    public List<DebitoAutomaticoLog> historial;

    @ManyToOne
    private Acta acta;

    @Basic
    @Column(nullable = false)
    private Boolean borrado;

    @Basic
    private Integer contadorEnvios;

//    @ManyToOne(fetch=FetchType.EAGER)
//    private ArchivoCargado archivo;


    public void log(DebitoAutomaticoLog log) {
        historial.add(log);
        log.setDebitoAutomatico(this);
    }

    public DebitoAutomaticoLog generarLog(String userLogin) {
        DebitoAutomaticoLog debitoAutomaticoLog = new DebitoAutomaticoLog();
        guardarEstadoAnterior(userLogin, debitoAutomaticoLog);
        log(debitoAutomaticoLog);
        return debitoAutomaticoLog;
    }

    private void guardarEstadoAnterior(String userLogin, DebitoAutomaticoLog debitoAutomaticoLog) {
        debitoAutomaticoLog.setBanco(getBanco());
        debitoAutomaticoLog.setCbu(getCbu());
        debitoAutomaticoLog.setEstado(getEstado());
        debitoAutomaticoLog.setFechaAcreditacion(getFechaAcreditacion());
        debitoAutomaticoLog.setFechaRecepcion(getFechaRecepcion());
        debitoAutomaticoLog.setFechaModificacion(new Date(System.currentTimeMillis()));
        debitoAutomaticoLog.setImporte(getImporte());
        debitoAutomaticoLog.setUsuario(userLogin);
    }

    public void updateEstadoAsString(String stringEstado) {
        this.estado = EstadoDebitoAutomatico.valueOf(stringEstado);
    }

    public boolean fechaAcreditacionCorrectaDebito() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return fechaAcreditacionCorrecta(sdf.format(getFechaAcreditacion()), getEstado().getOrdinal());
    }

    public boolean fechaAcreditacionCorrecta(String fechaAcreditacion, Integer estadoValue) throws Exception {
        if (EstadoDebitoAutomatico.values()[estadoValue].validateFechaAcreditacion()) {
            return validateFecha(fechaAcreditacion);
        }
        return true;
    }

    public boolean validateFecha(String fechaAcreditacion) throws Exception {
        long fechaActualMilli = Calendar.getInstance().getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(fechaAcreditacion);
        long fechaNuevaMilli = date.getTime();
        long diff = fechaNuevaMilli - fechaActualMilli;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays >= 0;
    }

    public String getFechaAcreditacionFormateada() {
        if (this.fechaAcreditacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.fechaAcreditacion);
        } else {
            return "";
        }
    }

}
