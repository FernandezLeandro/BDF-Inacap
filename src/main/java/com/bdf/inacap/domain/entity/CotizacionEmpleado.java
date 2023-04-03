package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;


@Data
@Entity
@Table(name = "CotizacionEmpleado")
public class CotizacionEmpleado{
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar desde;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar hasta;

    @Column(nullable=false)
    private Long minimoEmpleados;

    @Column
    private Long maximoEmpleados;

    @Column(nullable=false)
    private Double costo;

    @Column
    private Integer tipo;

    public Double cotizacion(Long cantidadEmpleados) {
        return Math.max(this.costo	* this.cantEmpleadosCotizables(cantidadEmpleados), 0);
    }

    public Double cotizacionNegativa(Long cantidadEmpleados) {
        return this.costo	* this.cantEmpleadosCotizables(cantidadEmpleados);
    }

    public Long cantEmpleadosCotizables(Long cantEmpleados) {
        Long maximo = this.maximoEmpleados == null	|| cantEmpleados < this.maximoEmpleados ? cantEmpleados	: this.maximoEmpleados;
        return maximo - this.minimoEmpleados;
    }
}
