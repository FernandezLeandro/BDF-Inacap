package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(unique = true, nullable = false)
    private String nombre;

}
