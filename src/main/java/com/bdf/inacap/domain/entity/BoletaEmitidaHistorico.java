package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
public class BoletaEmitidaHistorico{
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Calendar fechaTransaccion;

    @Column(name="periodo")
    private String periodo;

    @Column(name="vencimiento")
    private Calendar vencimiento;

    @Column(nullable = false, name="habilitadoEmision")
    private Boolean habilitadoEmision;

    @Column(nullable = false)
    private Long secuencia;

    @Enumerated
    @Column(nullable = false)
    private EstadoEnum estado;

    @Enumerated
    @Column(nullable = false)
    private Origen origen;

    @Column(nullable = false)
    private Long cantidadEmpleados;

    @Column(nullable = false,length = 20)
    private String cuit;

    @Column(nullable = false, length= 100)
    private String razonSocial;

    @Embedded
    private Domicilio domicilio;

    @Column(nullable = false)
    private Calendar primerPago;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar segundoPago;

    @Column(nullable = false)
    private Double valorBasico = 0D;

    @Column(nullable = false)
    private Double intereses1erPago = 0D;

    private Double intereses2doPago = 0D;

    @Column(nullable = true, length = 48)
    private String codigoBarras;

    @Transient
    private List<CotizacionEmpleado> cotizacionesEmpleados;

    @Transient
    private List<CotizacionInteres> cotizacionesIntereses;

    @Transient
    private int dias2doPgo;
    
    @Column(nullable = false)
    private Long tipo;

    @Column(nullable = true, length = 50)
    private String entidadCobro;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private ArchivoCargado archivo;
    
    @Basic
    @Column(nullable=true)
    private Calendar fechaPagoReal;
    
    @Basic
    @Column(nullable = false)
    private Long cheque = 0L;

    @ManyToOne
    @JoinColumn(name="periodo_id",nullable = false)
    private Periodo periodoId;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable = false)
    private Empresa empresa;


    @Column(nullable = true, name="transferidoA")
    private Long transferidoA;

    
    public String getFechaPagoRealAsString() {
        return DateUtil.getFechaAsString(this.fechaPagoReal, "es");
    }
    
    public BoletaEmitidaHistorico() {
        super();
        this.periodoId=new Periodo();
        this.cotizacionesEmpleados = new ArrayList<CotizacionEmpleado>();
        this.cotizacionesIntereses = new ArrayList<CotizacionInteres>();
    }


    public void emitir() {
        this.fechaTransaccion = Calendar.getInstance();
        this.estado = EstadoEnum.EMITIDA;
        this.cotizarBasico();
        this.cotizar1erPago();
    }

    protected void cotizarBasico() {
        for (CotizacionEmpleado cotizacionEmpleado : this.cotizacionesEmpleados) {
            this.valorBasico += cotizacionEmpleado.cotizacion(this.cantidadEmpleados);
        }
    }

    protected void cotizar2doPago() {
        this.segundoPago = (Calendar) this.primerPago.clone(); /* Genera la fecha de 2do pago */
        this.segundoPago.add(Calendar.DAY_OF_MONTH, this.dias2doPgo);
        for (CotizacionInteres cotizacion : cotizacionesIntereses) {
            this.intereses2doPago += cotizacion.cotizar(this.segundoPago, this.getVencimiento());
        }
    }

    protected void cotizar1erPago() {
        for (CotizacionInteres cotizacion : cotizacionesIntereses) {
            this.intereses1erPago += cotizacion.cotizar(this.primerPago, this.getVencimiento());
        }
        this.intereses1erPago *= this.valorBasico;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BoletaEmitidaHistorico clon = new BoletaEmitidaHistorico();
        clon.setId(null);
        clon.setArchivo(this.getArchivo());
        clon.setFechaTransaccion(this.getFechaTransaccion());
        clon.setPeriodo(this.getPeriodo());
        clon.setEstado(this.getEstado());
        clon.setOrigen(this.getOrigen());
        clon.setSecuencia(this.getSecuencia());
        clon.setCantidadEmpleados(this.getCantidadEmpleados());
        clon.setCuit(this.getCuit());
        clon.setRazonSocial(this.getRazonSocial());
        clon.setDomicilio(this.getDomicilio());
        clon.setPrimerPago(this.getPrimerPago());
        clon.setValorBasico(this.getValorBasico());
        clon.setIntereses1erPago(this.getIntereses1erPago());
        clon.setCotizacionesEmpleados(this.getCotizacionesEmpleados());
        clon.setCotizacionesIntereses(this.getCotizacionesIntereses());
        clon.setTipo(this.getTipo());
        clon.setEntidadCobro(this.getEntidadCobro());
        clon.setFechaPagoReal(this.getFechaPagoReal());
        clon.setCheque(this.getCheque());
        clon.setCodigoBarras(this.getCodigoBarras());
        clon.setPeriodoId(this.periodoId);
        clon.setEmpresa(this.getEmpresa());

        /*
         * Faltan los seters y getters del segundo pago en caso de habilitarse. 
         * 
         * */
            
        return clon;
    }

}
