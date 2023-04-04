package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Observacion {
    OBSERVADO("CON OBS."), NO_OBSERVADO("SIN OBS.");

    private String descripcion;

    public String getName() {
        return name();
    }

}