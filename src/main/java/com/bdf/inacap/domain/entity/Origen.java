package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Origen {
    WEB(new Integer("0"),"Web"),
    MANUAL (new Integer("1"), "Manual"),
    RESTAURADO (new Integer("2"), "Restaurado"),
    PLAN (new Integer("3"), "Plan"),
    BATCH_AUMENTO (new Integer("4"), "Batch Aumento"),
    LEGAL (new Integer("5"), "Legal"),
    DEBITOAUTOMATICO (new Integer("6"), "Débito Automático");
    private Integer id;
    private String descripcion;


    public static Origen valueOf(Integer id){
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
                return Origen.DEBITOAUTOMATICO;
            default:
                return null;
        }
    }

    public static List<Origen> getOrigenesBoleta(){
        List<Origen> origenes= new ArrayList<Origen>();
        origenes.add(Origen.MANUAL);
        origenes.add(Origen.WEB);
        origenes.add(Origen.BATCH_AUMENTO);
        origenes.add(Origen.PLAN);
        origenes.add(Origen.DEBITOAUTOMATICO);
        return origenes;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}