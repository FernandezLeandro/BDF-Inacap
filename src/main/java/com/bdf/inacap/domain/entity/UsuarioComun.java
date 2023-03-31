package com.bdf.inacap.domain.entity;

import lombok.Data;

@Data
public class UsuarioComun extends Auditable {

    private String cuit;
    private String razonSocial;
    private String clave;
    private String mail;

}
