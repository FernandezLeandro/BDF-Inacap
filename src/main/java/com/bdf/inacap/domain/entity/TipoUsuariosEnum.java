package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoUsuariosEnum {
    USUARIO(1L, "usuaio"),
    EMPRESA(2L, "empresa"),
    CONTADOR(3L, "contador");

    private Long id;
    private String descripcion;

    public static String getDescriptionByID(Long id) {
        Integer ident = id.intValue();
        return TipoUsuariosEnum.values()[ident - 1].getDescripcion();
    }

    public static TipoUsuariosEnum getTipoUsuarioByID(Long id) {
        Integer ident = id.intValue();
        return TipoUsuariosEnum.values()[ident - 1];
    }
}
