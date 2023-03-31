package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
public class EventoLegal extends Auditable {

    @ManyToOne
    private Empresa empresa;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Periodo> periodos;

    @Enumerated
    @Column(nullable = false)
    private EstadoEventoLegal estado;

    public EventoLegal(Empresa empresa, List<Periodo> periodos) {
        this.setEmpresa(empresa);
        this.setPeriodos(periodos);
        this.setEstado(EstadoEventoLegal.ABIERTO);
    }
}