package com.bdf.inacap.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadoItemLegal {
    //TODO: Atributos deberian ser privados?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Basic
    @Column(nullable = false, unique = true)
    String nombre;

    @Basic
    @Column(nullable = false)
    String descripcion;

}