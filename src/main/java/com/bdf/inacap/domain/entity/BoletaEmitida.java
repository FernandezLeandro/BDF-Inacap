package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@SuppressWarnings("serial")
@Data
@Entity
@Table(name="BoletaEmitida")
public class BoletaEmitida{

    @Transient
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Column(nullable = false,name="cantidadEmpleados")
    private Long cantidadEmpleados;

    @Column(nullable = true, length = 48,name="codigoBarras")
    private String codigoBarras;

    @Column(nullable = false,length = 20,name="cuit")
    private String cuit;

    @Enumerated
    @Column(nullable = false,name="estado")
    private EstadoEnum estado;

    @Enumerated
    @Column(nullable = false,name="origen")
    private OrigenEnum origen;

    @ManyToOne
    @JoinColumn(name="periodo_id",nullable = false)
    private Periodo periodoId;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable = false)
    private Empresa empresa;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false,name="fechaTransaccion")
    private Calendar fechaTransaccion;

    @Column(nullable = false,name="intereses1erPago")
    private Double intereses1erPago = 0D;

    @Column(name="intereses2doPago")
    private Double intereses2doPago = 0D;

    @Embedded
    private Domicilio domicilio;

    @Column(name="periodo")
    private String periodo;

    @Column(nullable = false, name="habilitadoEmision")
    private Boolean habilitadoEmision;

    @Column(name="vencimiento")
    private Date vencimiento;

    @Column(name="secuencia")
    private Long secuencia;

    @Temporal(TemporalType.DATE)
    @Column(nullable=true,name="fechaPagoReal")
    private Calendar fechaPagoReal;

    @Temporal(TemporalType.DATE)
    @Column(nullable=false,name="primerPago")
    private Calendar primerPago;

    @Column(nullable = false, length= 100,name="razonSocial")
    private String razonSocial;

    @Column(nullable = false, name="tipo")
    private Long tipo;

    @Column(nullable = false,name="valorBasico")
    private Double valorBasico;

    @Basic
    @Column(nullable = false)
    private Long cheque = 0L;

    public Long getCheque() {
        return cheque;
    }

    public void setCheque(Long cheque) {
        this.cheque = cheque;
    }

    @Transient
    private List<CotizacionEmpleado> cotizacionesEmpleados;

    @Transient
    private List<CotizacionInteres> cotizacionesIntereses;

    public void addAllCotizacionesEmpleados(
            List<CotizacionEmpleado> cotizacionesEmpleados) {
        this.cotizacionesEmpleados = cotizacionesEmpleados;
    }

    public void addAllCotizacionesIntereses(
            List<CotizacionInteres> cotizacionesIntereses) {
        this.cotizacionesIntereses = cotizacionesIntereses;
    }

    public void emitir() {
        this.fechaTransaccion = Calendar.getInstance();
        this.estado = EstadoEnum.EMITIDA;
        this.cotizarBasico();
        this.cotizar1erPago();
    }

    protected void cotizarBasico() {
        if (valorBasico==null){
            valorBasico=0d;
        }
        for (CotizacionEmpleado cotizacionEmpleado : this.cotizacionesEmpleados) {
            this.valorBasico += cotizacionEmpleado.cotizacion(this.cantidadEmpleados);
        }
    }

    protected void cotizar1erPago() {
        if (intereses1erPago==null){
            intereses1erPago=0d;
        }
        for (CotizacionInteres cotizacion : cotizacionesIntereses) {
            this.intereses1erPago += cotizacion.cotizar(this.primerPago, this.periodoId.getVencimiento());
        }
        this.intereses1erPago *= this.valorBasico;
    }

    public String getPrimerPagoString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(getPrimerPago().getTime());
    }


    public String getFechaEmision(){
        if (getFechaTransaccion()!=null)
            return sdf.format(new Date(getFechaTransaccion().getTimeInMillis()));
        else
            return "";
    }

    public String getFechaPagoRealString(){
        if (getFechaPagoReal()!=null)
            return sdf.format(new Date(getFechaPagoReal().getTimeInMillis()));
        else
            return "";
    }

    public String getMontoBasico(){
        return Utils.completarDosDecimales(getValorBasico());
    }

    public String getMontoIntereses(){
        return Utils.completarDosDecimales(getIntereses1erPago());
    }


    public String getMonto(){
        Double total = getValorBasico() + getIntereses1erPago();
        total = total * 100;
        // Round to the nearest integer.
        long tmp = Math.round(total);
        double dbl = new Double(tmp);
        return Utils.completarDosDecimales(dbl / 100);
    }

    public String getFechaEmisionAsString() {
        return DateUtil.getFechaAsString(this.fechaTransaccion, "es");
    }

    public String getPrimerPagoAsString() {
        return DateUtil.getFechaAsString(this.primerPago, "es");
    }

    public String getFechaPagoRealAsString() {
        return DateUtil.getFechaAsString(this.fechaPagoReal, "es");
    }

    public void setPeriodoColumns(Periodo periodo){
        this.setHabilitadoEmision(periodo.getHabilitadoEmision());
        this.setVencimiento(periodo.getVencimiento().getTime());
        this.setPeriodo(periodo.getPeriodo());
        this.setPeriodoId(periodo);
    }

}
