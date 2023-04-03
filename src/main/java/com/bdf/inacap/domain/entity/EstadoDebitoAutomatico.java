package com.bdf.inacap.domain.entity;

import lombok.Getter;

@Getter
public enum EstadoDebitoAutomatico {

    PENDIENTE("Pendiente") {
        @Override
        public boolean validateFechaAcreditacion() {
            return true;
        }
    },

    A_DEBITAR("A Debitar") {
        @Override
        public boolean validateFechaAcreditacion() {
            return true;
        }
    },

    ENVIADO("Enviado"),

    RECHAZADO("Rechazado"),

    VENCIDO("Vencido"),

    PAGADO("Pagado");

    private String descripcion;

    private EstadoDebitoAutomatico(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOrdinal() {
        return ordinal();
    }

    public String getName() {
        return name();
    }

    public String getDescripcion() {
        return descripcion;
    }


    public static String getDescriptionFromValue(Long value) {
        if (PENDIENTE.ordinal() == value)
            return PENDIENTE.getDescripcion();
        else if (A_DEBITAR.ordinal() == value)
            return A_DEBITAR.getDescripcion();
        else if (ENVIADO.ordinal() == value)
            return ENVIADO.getDescripcion();
        else if (RECHAZADO.ordinal() == value)
            return RECHAZADO.getDescripcion();
        else if (VENCIDO.ordinal() == value)
            return VENCIDO.getDescripcion();
        else if (PAGADO.ordinal() == value)
            return PAGADO.getDescripcion();
        else
            return "Invalido";
    }

    public boolean validateFechaAcreditacion() {
        return false;
    }

}
