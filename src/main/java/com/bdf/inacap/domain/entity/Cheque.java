package com.bdf.inacap.domain.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Cheque")
public class Cheque {

    @Basic
    private Integer actaId;

    @ManyToOne
    private Banco bancoDeposito;

    @Basic
    private String nroCheque;

    @Temporal(TemporalType.DATE)
    private Date fechaAcreditacion;

    @Basic
    private Observacion estadoObservacion;

    @Basic
    private String observaciones;

    @Basic
    private Double importe;

    @Basic
    private String nroCuenta;
    
    @Basic
    private String codigoPostal;
    
    @ManyToOne
    private Empresa empresa;

    @Basic
    @Column(nullable=false)
    private Boolean borrado;

    
    public String getFechaAcreditacionFormateada(){
		if(this.fechaAcreditacion != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(this.fechaAcreditacion);
		} else {
			return "";
		}
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((actaId == null) ? 0 : actaId.hashCode());
		result = prime * result
				+ ((bancoDeposito == null) ? 0 : bancoDeposito.hashCode());
		result = prime * result
				+ ((nroCheque == null) ? 0 : nroCheque.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		if (!super.equals(obj))
//			return false;
		if (getClass() != obj.getClass())
			return false;
		Cheque other = (Cheque) obj;
		if (actaId == null) {
			if (other.actaId != null)
				return false;
		} else if (!actaId.equals(other.actaId))
			return false;
		if (bancoDeposito == null) {
			if (other.bancoDeposito != null)
				return false;
		} else if (!bancoDeposito.equals(other.bancoDeposito))
			return false;
		if (nroCheque == null) {
			if (other.nroCheque != null)
				return false;
		} else if (!nroCheque.equals(other.nroCheque))
			return false;
		return true;
	}
    
}
