package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
@Data
public class Rol {

    @Basic
    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_menues")
    private List<Menu> menues;

    @Basic
    @Column(name="mirror")
    private boolean mirror;

    @Basic
    @Column(name="produccion")
    private boolean produccion;

    @Basic
    @Column(name="fiscal")
    private boolean fiscal;

    @Basic
    @Column(name="aprobador")
    private boolean aprobador;

    @Basic
    @Column(name="adminFiscal")
    private boolean adminFiscal;

}

