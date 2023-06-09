package com.bdf.inacap.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Basic;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Acta acta;

    @ManyToOne
    private Periodo periodo;

    @ManyToOne
    private BoletaEmitida boleta;

    @Enumerated
    @Column(nullable = false)
    private EstadoEnum estadoBoleta;

    private Integer empleadosAbonado;

    private Integer empleadosAuditado;

    private Double nominalAPagar;

    private Double intereses;

    private Date fechaCalcIntereses;

    private Date fechaPago;

    @Transient
    private String fechaCalcInteresesString;

    @ManyToOne
    private Cheque cheque;

    @ManyToOne
    private DebitoAutomatico debitoAutomatico;

    @Column(nullable = false)
    private Boolean borrado;

    public String getFechaCalcInteresesString() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.getFechaCalcIntereses());
        } catch (Exception e) {
            return "";
        }
    }

    public void setFechaCalcInteresesString(String fechaCalcInteresesString) {
        this.fechaCalcInteresesString = fechaCalcInteresesString;
    }

    //TODO: Verificar el comentario line 97
    public BoletaEmitida generarBoleta(Long sec, List<CotizacionEmpleado> cotizacionesEmpleados) {
        BoletaEmitida boleta = new BoletaEmitida();
        Long empleados = (long) (this.empleadosAuditado - this.empleadosAbonado);
        boleta.setPeriodoColumns(this.getPeriodo());
        boleta.setEmpresa(this.getActa().getEmpresa()); //agregado el 27/01/2016 porque sino rompia en la base si quedaba en null
        this.setEmpresa(boleta, this.getActa().getEmpresa());
        boleta.setCantidadEmpleados(empleados);
        Calendar fAcreditacion = Calendar.getInstance();
        fAcreditacion.setTime(getFechaAcreditacion());
        boleta.setFechaPagoReal(fAcreditacion);
        boleta.setSecuencia(sec);
        Calendar fPrimerPago = Calendar.getInstance();
        fPrimerPago.setTime(this.getFechaCalcIntereses());
        boleta.setPrimerPago(fPrimerPago);
        boleta.setFechaTransaccion(Calendar.getInstance());
        boleta.setOrigen(Origen.PLAN);
        Double valorBasico = 0.0;
        for (CotizacionEmpleado cotizacionEmpleado : cotizacionesEmpleados) {
            valorBasico += cotizacionEmpleado.cotizacionNegativa(empleados);
        }
        boleta.setValorBasico(valorBasico);
        boleta.setIntereses1erPago(this.getIntereses());
        boleta.setCheque(9982L); // 1L
        boleta.setTipo(6L);
        if (tieneCheque()) {
            boleta.setEstado(EstadoEnum.PAGADA);
        } else {
            boleta.setEstado(EstadoEnum.EN_DEBITO);
        }
        return boleta;
    }

    private void setEmpresa(BoletaEmitida boleta, Empresa empresa) {
        boleta.setCuit(empresa.getCuit());
        boleta.setDomicilio(empresa.getDomicilio());
        boleta.setRazonSocial(empresa.getRazonSocial());
    }

    public Double getImporte() {
        if (getCheque() != null)
            return getCheque().getImporte();
        else if (getDebitoAutomatico() != null)
            return getDebitoAutomatico().getImporte();
        else
            return 0d;
    }

    public boolean tieneCheque() {
        return getCheque() != null && getCheque().getId() != null;
    }

    public String getBancoDeposito() {
        if (getCheque() != null)
            return getCheque().getBancoDeposito().getNombre();
        else if (getDebitoAutomatico() != null && getDebitoAutomatico().getBancoDeposito() != null)
            return getDebitoAutomatico().getBancoDeposito().getNombre();
        else
            return "";
    }

    public String getNroChequeCbu() {
        if (getCheque() != null)
//        	if (tieneCheque())
            return getCheque().getNroCheque();
        else if (getDebitoAutomatico() != null)
            return getDebitoAutomatico().getCbu();
        else
            return "";
    }

    public String getFechaAcreditacionFormateada() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.getFechaAcreditacion());
        } catch (Exception e) {
            return "";
        }
    }

    public Date getFechaAcreditacion() {
        if (getCheque() != null)
            return getCheque().getFechaAcreditacion();
        else
            return getDebitoAutomatico().getFechaAcreditacion();
    }

    public boolean tieneFormaDePago() {
        return getCheque() != null || getDebitoAutomatico() != null;
    }

    public boolean tieneDebitoAutomatico() {
        return getDebitoAutomatico() != null;
    }

    public String getNroChequeCortado() {
        if (getNroChequeCbu().length() > 8) {
            return getNroChequeCbu().substring(getNroChequeCbu().length() - 8);
        } else {
            return getNroChequeCbu();
        }
    }

    public void desasociarFormaDePagoAnterior() {

        if (tieneFormaDePago()) {
            if (tieneCheque()) {
                setCheque(null);
            }
            setDebitoAutomatico(null);
        }

    }

    public boolean estaEntrePeriodo(Long periodoDesdeId, Long periodoHastaId) {
        return (getPeriodo().getId() <= periodoHastaId && getPeriodo().getId() >= periodoDesdeId);
    }

}

