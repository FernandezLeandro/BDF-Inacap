package com.bdf.inacap.log;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;


@Data
@Entity(name = "ArchivoCargado")
public class ArchivoCargado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar fechaRecepcion;

    @Basic
    @Column(nullable = false, length = 100)
    private String usuario;

    @Basic
    @Column(nullable = false, length = 100)
    private String entidadRecaudadora;

    @Basic
    @Column(nullable = false, length = 100)
    private String nombreArchivo;

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ArchivoCargado other = (ArchivoCargado) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
