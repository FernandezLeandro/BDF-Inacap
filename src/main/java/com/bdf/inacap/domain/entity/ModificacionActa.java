package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Basic;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@AllArgsConstructor
@Data
public class ModificacionActa extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Acta acta;

    @Temporal(TemporalType.TIMESTAMP)
    Date fechaHoraModificacion;

    @Basic
    String nombreUsuario;

    public String getFechaHoraModificacionString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm");
        return sdf.format(fechaHoraModificacion);
    }
}
