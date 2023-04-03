package com.bdf.inacap.domain.entity;

import com.bdf.inacap.utils.FormateadorDeFechas;
import jakarta.persistence.*;
import lombok.Data;

import java.text.DateFormat;
import java.util.Date;

@Data
@Entity
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entidad")
    private String entidad;

    @Column(name = "entidadId")
    private Long entidadId;

    @Column(name = "atributo")
    private String atributo;

    @Column(name = "valorNuevo")
    private String valorNuevo;

    @Column(name = "valorAnterior")
    private String valorAnterior;

    @Column(name = "usuario")
    private String usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "action")
    private String action;

    @Column(name = "operacion")
    private String operacion;

    @Lob
    @Column(name = "textoSQL")
    private String textoSQL;

    @Column(name = "comentario")
    private String comentario;


    public String getFechaString() {
        DateFormat sdf = FormateadorDeFechas.getDateFormater("es");
        if (this.getFecha() != null)
            return sdf.format(this.getFecha());
        else
            return "";
    }

}
