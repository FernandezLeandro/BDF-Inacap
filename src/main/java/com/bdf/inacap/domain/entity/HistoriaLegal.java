package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@SuppressWarnings("serial")
@Entity
@Data
public class HistoriaLegal {

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;

    @ManyToOne
    ItemLegal item;

    @ManyToOne
    EstadoItemLegal estadoItem;

    @ManyToOne
    Usuario usuario;
}