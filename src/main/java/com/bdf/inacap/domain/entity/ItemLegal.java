package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemLegal extends Auditable {

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