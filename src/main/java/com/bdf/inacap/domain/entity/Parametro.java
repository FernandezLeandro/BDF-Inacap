package com.bdf.inacap.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="Parametro")
@Data
public class Parametro extends Auditable{
    @Column(nullable = false,length = 60)
    private String clave;

    @Column(nullable = false,length = 200)
    private String valor;

    @Column(nullable = false,length = 20)
    private String user;

    @Column(nullable=false)
    private Timestamp lastUpdate;

}

