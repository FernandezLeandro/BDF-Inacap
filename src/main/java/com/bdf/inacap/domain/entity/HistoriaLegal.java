package com.bdf.inacap.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoriaLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;

    @ManyToOne
    ItemLegal item;

    @ManyToOne
    EstadoItemLegal estadoItem;

    @ManyToOne
    Usuario usuario;

    public HistoriaLegal(ItemLegal item, Usuario usuario) {
        this.setItem(item);
        this.setUsuario(usuario);
        this.setFecha(LocalDateTime.now());
        this.setEstadoItem(item.getEstado());
    }
}