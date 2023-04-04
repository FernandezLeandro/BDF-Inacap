package com.bdf.inacap.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioComun {

    private String cuit;
    private String razonSocial;
    private String clave;
    private String mail;

}
