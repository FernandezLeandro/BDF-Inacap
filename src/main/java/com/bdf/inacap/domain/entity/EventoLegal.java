package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class EventoLegal extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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