package com.bdf.inacap.domain.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum AdeudaEnum {
        TODOS(1,"Todos"),
        SI(2,"Si"),
        NO(3, "No");


    private Integer id;
    private String descripcion;

    AdeudaEnum(Integer id, String descripcion){
        this.id=id;
        this.descripcion=descripcion;
    }

}
