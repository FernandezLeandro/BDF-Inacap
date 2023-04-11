package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


@Entity
@AllArgsConstructor
@Getter
@Setter
public class ItemPlanLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    PlanDeDeudaLegal plan;

    @OneToOne
    BoletaEmitida boleta;

    @Column(nullable = false)
    Long cantidadEmpleados;

    @Column(nullable = false)
    Double monto;

    @Column(nullable = false)
    Double intereses;

    @OneToOne
    Periodo periodo;

    @ManyToMany
    List<BoletaEmitida> boletasPagadas;

    public ItemPlanLegal() {
        this.setCantidadEmpleados(0L);
        this.setMonto(0.0);
        this.setIntereses(0.0);
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