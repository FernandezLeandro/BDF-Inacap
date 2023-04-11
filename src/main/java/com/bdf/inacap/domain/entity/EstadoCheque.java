package com.bdf.inacap.domain.entity;

import lombok.*;

@Getter
public enum EstadoCheque {
	EN_CARTERA("En Cartera"),ACREDITADO("Acreditado"),DEPOSITADO("Depositado"),RECHAZADO("Rechazado"),REEMPLAZADO("Reemplazado"), EN_CANJE("En Canje");
	
	private String descripcion;
	
	EstadoCheque(String descripcion) {
		this.descripcion = descripcion;
	}

	public static String getDescriptionFromValue(Long value){
		if(EN_CARTERA.ordinal()==value)
			return EN_CARTERA.getDescripcion();
		else if(ACREDITADO.ordinal()==value)
			return ACREDITADO.getDescripcion();
		else if(DEPOSITADO.ordinal()==value)
			return DEPOSITADO.getDescripcion();
		else if(RECHAZADO.ordinal()==value)
			return RECHAZADO.getDescripcion();
		else if(REEMPLAZADO.ordinal()==value)
			return REEMPLAZADO.getDescripcion();
		else if(EN_CANJE.ordinal()==value)
			return EN_CANJE.getDescripcion();
		else
			return "Invalido";
	}

}
