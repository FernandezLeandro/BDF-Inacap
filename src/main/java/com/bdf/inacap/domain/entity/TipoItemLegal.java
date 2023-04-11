package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class TipoItemLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String nombre;

    @Column(nullable = false, length = 2)
    String codigo;

    @Column(nullable = false)
    String descripcion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_id", unique = true)
    Set<EstadoItemLegal> estados;

    public TipoItemLegal() {
        this.setEstados(new HashSet<EstadoItemLegal>());
    }

    public TipoItemLegal(String nombre, String codigo, String descripcion) {
        this();
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
    }

    @Transient
    public EstadoItemLegal getEstadoItemLegalInicial() {
        EstadoItemLegal estadoItemLegalInicial = null;
        for (EstadoItemLegal estado : this.getEstados())
            if (estado.getNombre().equalsIgnoreCase("INICIAL"))
                estadoItemLegalInicial = estado;
        return estadoItemLegalInicial;
    }

}