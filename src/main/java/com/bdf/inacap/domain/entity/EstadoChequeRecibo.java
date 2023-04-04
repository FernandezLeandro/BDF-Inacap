package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@AllArgsConstructor
public enum EstadoChequeRecibo {
	Activo, Cancelado;
}
