package com.bdf.inacap.domain.entity;

public enum EstadoEventoLegal {
    ABIERTO,CERRADO;

    Long getId(){
        return new Long(this.ordinal());
    }
}