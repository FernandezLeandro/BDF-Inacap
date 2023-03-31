package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Data
public class TipoItemLegal extends Auditable {

    @Basic
    @Column(nullable=false)
    String nombre;

    @Basic
    @Column(nullable=false,length=2)
    String codigo;

    @Basic
    @Column(nullable=false)
    String descripcion;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="tipo_id", unique = true)
    Set<EstadoItemLegal> estados;

    public TipoItemLegal(){
        this.setEstados(new HashSet<EstadoItemLegal>());
    }

    public TipoItemLegal(String nombre, String codigo, String descripcion) {
        this();
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
    }

    @Transient
    public EstadoItemLegal getEstadoItemLegalInicial(){
        EstadoItemLegal estadoItemLegalInicial = null;
        for (EstadoItemLegal estado : this.getEstados())
            if (estado.getNombre().equalsIgnoreCase("INICIAL"))
                estadoItemLegalInicial = estado;
        return estadoItemLegalInicial;
    }

}