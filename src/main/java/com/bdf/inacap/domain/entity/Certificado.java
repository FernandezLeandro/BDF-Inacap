package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    Acta acta;

    @ManyToOne(fetch = FetchType.EAGER)
    Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime fecha;

    public Certificado() {
        fecha = LocalDateTime.now();
    }

    public Certificado(Usuario usuario, Acta acta) {
        this();
        this.usuario = usuario;
        this.acta = acta;
    }


}
