package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer actaId;

    @ManyToOne
    private Banco bancoDeposito;

    private String nroCheque;

    @Temporal(TemporalType.DATE)
    private Date fechaAcreditacion;

    private Observacion estadoObservacion;

    private String observaciones;

    private Double importe;

    private String nroCuenta;

    private String codigoPostal;

    @ManyToOne
    private Empresa empresa;

    @Column(nullable = false)
    private Boolean borrado;


    public String getFechaAcreditacionFormateada() {
        if (this.fechaAcreditacion != null) {
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
