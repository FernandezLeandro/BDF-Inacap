package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


@SuppressWarnings("serial")
@Entity
@Data
public class ItemPlanLegal {

    @ManyToOne
    PlanDeDeudaLegal plan;

    @OneToOne
    BoletaEmitida boleta;

    @Basic
    @Column(nullable = false)
    Long cantidadEmpleados;

    @Basic
    @Column(nullable = false)
    Double monto;

    @Basic
    @Column(nullable = false)
    Double intereses;

    @OneToOne
    Periodo periodo;

    @ManyToMany
    List<BoletaEmitida> boletasPagadas;

    public void setPlan(PlanDeDeudaLegal plan) {
        this.plan = plan;
    }

    public PlanDeDeudaLegal getPlan() {
        return plan;
    }

    public ItemPlanLegal() {
        this.setCantidadEmpleados(0L);
        this.setMonto(0.0);
        this.setIntereses(0.0);
//        this.periodo = new Periodo();
//        this.id = 0l;
    }

    public ItemPlanLegal(Periodo periodo, List<BoletaEmitida> boletas) {
        this();
        this.setPeriodo(periodo);
        this.setBoletasPagadas(boletas);
    }

    public Long getCantidadEmpleadosAbonados() {
        Long cantidad = 0L;
        if (this.boletasPagadas != null) {
            for (BoletaEmitida boleta : this.boletasPagadas) {
                if (boleta.getCantidadEmpleados() != null)
                    cantidad += boleta.getCantidadEmpleados();
            }
        }
        return cantidad;
    }

    public String getMontoFormateado() {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        DecimalFormat df = (DecimalFormat) nf;
        return df.format(this.monto);
    }
}