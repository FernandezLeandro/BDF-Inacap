package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoPlanDeDeuda {
    PENDIENTE_APROBACION("Pendiente de Aprobación"), APROBADO("Aprobado"), RECHAZADO("Rechazado"), ARCHIVADO("Archivado"), VENCIDO("Vencido"), PAGADO("Pagado");

    private String leyenda;

    //TODO: Se podria mejorar, uso de muchos if/else
    //Devuelve acorde a la leyenda, el enum? Es decir si la leyenda es "Pendiente de Aprobacion" devuelve PENDIENTE_APROBACION?
    public static EstadoPlanDeDeuda getEstadoPlan(String leyenda) {
        EstadoPlanDeDeuda estado = null;
        if (EstadoPlanDeDeuda.APROBADO.name().equalsIgnoreCase(leyenda))
            estado = EstadoPlanDeDeuda.APROBADO;
        else if (EstadoPlanDeDeuda.PENDIENTE_APROBACION.name().equalsIgnoreCase(leyenda))
            estado = EstadoPlanDeDeuda.PENDIENTE_APROBACION;
        else if (EstadoPlanDeDeuda.APROBADO.name().equalsIgnoreCase(leyenda))
            estado = EstadoPlanDeDeuda.APROBADO;
        else if (EstadoPlanDeDeuda.ARCHIVADO.name().equalsIgnoreCase(leyenda))
            estado = EstadoPlanDeDeuda.ARCHIVADO;
        else if (EstadoPlanDeDeuda.VENCIDO.name().equalsIgnoreCase(leyenda))
            estado = EstadoPlanDeDeuda.VENCIDO;
        else if (EstadoPlanDeDeuda.PAGADO.name().equalsIgnoreCase(leyenda))
            estado = EstadoPlanDeDeuda.PAGADO;
        return estado;
    }

    public static String devolverLeyendaXOrdinal(Integer ordinal) {
        String leyenda = null;

        if (ordinal.equals(0))
            leyenda = EstadoPlanDeDeuda.PENDIENTE_APROBACION.getLeyenda();
        else if (ordinal.equals(1))
            leyenda = EstadoPlanDeDeuda.APROBADO.getLeyenda();
        else if (ordinal.equals(2))
            leyenda = EstadoPlanDeDeuda.RECHAZADO.getLeyenda();
        else if (ordinal.equals(3))
            leyenda = EstadoPlanDeDeuda.ARCHIVADO.getLeyenda();
        else if (ordinal.equals(4))
            leyenda = EstadoPlanDeDeuda.VENCIDO.getLeyenda();
        else if (ordinal.equals(5))
            leyenda = EstadoPlanDeDeuda.PAGADO.getLeyenda();

        return leyenda;
    }

}