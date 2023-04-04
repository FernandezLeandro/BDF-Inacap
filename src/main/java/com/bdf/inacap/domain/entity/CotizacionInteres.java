package com.bdf.inacap.domain.entity;

import com.bdf.inacap.utils.DateUtil;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;


@Data
@Entity(name = "CotizacionInteres")
public class CotizacionInteres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar desde;

    @Temporal(TemporalType.DATE)
    private Calendar hasta;

    @Basic
    @Column(nullable = false)
    private Double porcentaje;

    protected Double interesDiario() {
        return porcentaje / 30D;
    }

    public Double cotizar(Calendar fechaPago, Calendar fechaVencimiento) {
        Calendar fechaDesde = this.desde.before(fechaVencimiento) ? fechaVencimiento : this.desde;
        Calendar fechaHasta = fechaPago.before(this.hasta) || this.hasta == null ? fechaPago : this.hasta;
        Double diasTranscurridos = Double.valueOf(DateUtil.obtenerDifferenciaDias(fechaDesde, fechaHasta));
        return Math.max(this.interesDiario() * diasTranscurridos, 0D);
    }
}
