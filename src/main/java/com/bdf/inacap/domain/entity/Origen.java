package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Origen {
    //TODO: Habia dos diferentes versiones de Origen, verificar si esta bien, usa switch case
    WEB(0, "Web"),
    MANUAL(1, "Manual"),
    RESTAURADO(2, "Restaurado"),
    PLAN(3, "Plan"),
    BATCH_AUMENTO(4, "Batch Aumento"),
    LEGAL(5, "Legal"),
    DEBITO_AUTOMATICO(6, "Débito Automático");
    private Integer id;
    private String descripcion;

    //TODO: Averiguar si java retorna todos los enums
    public static List<Origen> getOrigenesBoleta() {
        List<Origen> origenes = new ArrayList<Origen>();
        origenes.add(Origen.MANUAL);
        origenes.add(Origen.WEB);
        origenes.add(Origen.BATCH_AUMENTO);
        origenes.add(Origen.PLAN);
        origenes.add(Origen.DEBITO_AUTOMATICO);
        return origenes;
    }
}