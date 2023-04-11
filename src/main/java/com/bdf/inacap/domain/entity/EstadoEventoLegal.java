package com.bdf.inacap.domain.entity;

import lombok.*;

@Getter
@AllArgsConstructor
public enum EstadoEventoLegal {
    ABIERTO, CERRADO;

    Long getId() {
        return Long.valueOf(this.ordinal());
    }
}