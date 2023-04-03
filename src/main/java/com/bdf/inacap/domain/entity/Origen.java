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

    public static Origen valueOf(Integer id) {
        switch (id.intValue()) {
            case 0:
                return Origen.WEB;
            case 1:
                return Origen.MANUAL;
            case 2:
                return Origen.RESTAURADO;
            case 3:
                return Origen.PLAN;
            case 4:
                return Origen.BATCH_AUMENTO;
            case 5:
                return Origen.LEGAL;
            case 6:
                return Origen.DEBITO_AUTOMATICO;
            default:
                return null;
        }
    }

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