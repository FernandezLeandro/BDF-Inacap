package com.bdf.inacap.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ExentoEnum {
    NO("NO",true,false), CAME ("SI (CAME)", false,true), FAECYS("SI (FAECYS)",false,true), BAJA("SI (BAJA)", false,true),
    CAC("SI (CAC)",false,true), EMPLEADOS0("SI (EMPLEADOS 0)",true,true), AFIP("SI (AFIP)", false, true), CONCURSO("SI (CONCURSO)",true,true);

    //Mensaje que muestra en las aplicaciones.
    private String mensaje;
    //Son las empresas que INACAP considera aportantes.
    //Se utiliza para el cálculo de algunos reportes y son las que pueden loguearse al sistema.
    private Boolean aportante;
    //Si es un estado de tipo eximición.
    //Se utiliza en algunos lados para ver si mostrar la fechaExento.
    private Boolean exento;

    ExentoEnum(String mensaje, Boolean aportante, Boolean exento){
        this.aportante = aportante;
        this.mensaje = mensaje;
        this.exento = exento;
    }

    public String getName(){
        return name();
    }

    public Integer getOrdinal(){
        return this.ordinal();
    }

    public static ExentoEnum valueOf(int valor) {
        switch (valor) {
            case 0:
                return ExentoEnum.NO;
            case 1:
                return ExentoEnum.CAME;
            case 2:
                return ExentoEnum.FAECYS;
            case 3:
                return ExentoEnum.BAJA;
            case 4:
                return ExentoEnum.CAC;
            case 5:
                return ExentoEnum.EMPLEADOS0;
            case 6:
                return ExentoEnum.AFIP;
            case 7:
                return ExentoEnum.CONCURSO;
            default:
                return null;
        }
    }

    public static boolean isAportante(ExentoEnum estado){
        return ExentoEnum.getAportantes().contains(estado);
    }

    public static List<ExentoEnum> getAportantes(){
        List<ExentoEnum> resultado = new ArrayList<ExentoEnum>();
        for (ExentoEnum  exento: ExentoEnum.values()) {
            if(exento.getAportante()){
                resultado.add(exento);
            }
        }
        return resultado;
    }
}