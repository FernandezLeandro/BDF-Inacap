package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "actividad")
    private String actividad;


}
