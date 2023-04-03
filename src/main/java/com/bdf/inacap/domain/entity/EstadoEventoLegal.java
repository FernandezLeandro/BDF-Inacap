package com.bdf.inacap.domain.entity;

import lombok.Getter;

@Getter
public enum EstadoEventoLegal {
    ABIERTO, CERRADO;

    Long getId() {
        return new Long(this.ordinal());
    }
}