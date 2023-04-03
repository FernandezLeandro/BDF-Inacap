package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Parametro extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String clave;

    @Column(nullable = false, length = 200)
    private String valor;

    @Column(nullable = false, length = 20)
    private String user;

    @Column(nullable = false)
    private Timestamp lastUpdate;
}

