package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "Cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(unique = true, nullable = false)
    private String nombre;

    @Basic
    @Column(unique = true, nullable = false)
    private String numero;

    @ManyToOne
    private Banco banco;


}
