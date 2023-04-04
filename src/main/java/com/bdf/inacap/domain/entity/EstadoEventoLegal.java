package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@AllArgsConstructor
public enum EstadoEventoLegal {
    ABIERTO, CERRADO;

    Long getId() {
        return Long.valueOf(this.ordinal());
    }
}