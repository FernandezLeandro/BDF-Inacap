package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoContadorEnum {
    ESTUDIO_CONTABLE(1,"estudioContable"),
    PERSONA_FISICA(2,"personaFisica");

    private Integer id;
    private String descripcion;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static TipoContadorEnum getTipoContadorByID(Long id){
        Integer ident = id.intValue();
        return TipoContadorEnum.values()[ident -1];
    }
}

