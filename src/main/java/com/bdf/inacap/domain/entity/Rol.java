package com.bdf.inacap.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Rol{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_menues")
    private List<Menu> menues;

    @Basic
    @Column(name = "mirror")
    private boolean mirror;

    @Basic
    @Column(name = "produccion")
    private boolean produccion;

    @Basic
    @Column(name = "fiscal")
    private boolean fiscal;

    @Basic
    @Column(name = "aprobador")
    private boolean aprobador;

    @Basic
    @Column(name = "adminFiscal")
    private boolean adminFiscal;
}

