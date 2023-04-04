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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModificacionActa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
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
