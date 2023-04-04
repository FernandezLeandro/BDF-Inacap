package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDePlanDeDeuda {
    NORMAL("Solicitud"), FISCALIZADOR("Solicitud de Fiscalizador");

    String leyenda;

    public static TipoDePlanDeDeuda getTipoPlan(String leyenda) {
        TipoDePlanDeDeuda tipo = null;

        if (TipoDePlanDeDeuda.FISCALIZADOR.name().equalsIgnoreCase(leyenda))
            tipo = FISCALIZADOR;
        else if (TipoDePlanDeDeuda.NORMAL.name().equalsIgnoreCase(leyenda))
            tipo = TipoDePlanDeDeuda.NORMAL;

        return tipo;
    }

    public static String DevolverLeyendaXOrdinal(Integer ordinal) {
        String leyenda = null;

        if (ordinal.equals(0))
            leyenda = TipoDePlanDeDeuda.NORMAL.getLeyenda();
        else if (ordinal.equals(1))
            leyenda = TipoDePlanDeDeuda.FISCALIZADOR.getLeyenda();

        return leyenda;
    }
}
