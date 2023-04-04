package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@AllArgsConstructor
public enum EstadoActa {
    ABIERTA,PRE_CERRADA,CERRADA,APROBADA, CANCELADA, APROBADA_CON_OBS
}