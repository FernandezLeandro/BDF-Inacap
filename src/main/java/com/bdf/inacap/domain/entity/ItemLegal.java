package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;


@Entity
@Data
public class ItemLegal extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    protected EventoLegal evento;

    @ManyToOne
    protected EstadoItemLegal estado;

    @ManyToOne
    protected TipoItemLegal tipo;

    @Basic
    @Column(nullable = false, length = 10)
    protected String codigoUsuario;

    @Basic
    @Column(nullable = false, length = 20)
    protected String nroProceso;
}