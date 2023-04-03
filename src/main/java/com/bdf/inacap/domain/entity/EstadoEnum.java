package com.bdf.inacap.domain.entity;

import lombok.Getter;

@Getter
public enum EstadoEnum {

    EMITIDA(0, "Emitida"),
    PAGADA(1, "Pagada"),
    ANULADA(2, "Anulada"),
    RECHAZADO(3, "Rechazado"),
    PRESENTADO(4, "Presentado"),
    LIBERADO(5, "Liberado"),
    EMPLEADOS0(6, "Empleados0"),
    MATERNIDAD(7, "Maternidad"),
    NOEMITIDA(8, "No Emitida"),
    CONCURSO(9, "Concurso"),
    ACREDITADO(10, "Acreditado"),
    EN_DEBITO(11, "En Débito");

    private Integer id;
    private String descripcion;

    EstadoEnum(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

}
