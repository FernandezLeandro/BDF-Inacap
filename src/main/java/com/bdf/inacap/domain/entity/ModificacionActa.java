package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("serial")
@Entity
@Table(name="ModificacionActa")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModificacionActa extends Auditable {

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
