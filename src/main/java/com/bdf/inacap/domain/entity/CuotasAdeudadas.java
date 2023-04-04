package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity(name = "CuotasAdeudadas")
@IdClass(CuotasAdeudadas.class)
public class CuotasAdeudadas implements Serializable {
    //TODO: REVISAR ESTO POR EL AMOR DE DIOS
    @Id
    @ManyToOne
    @JoinColumn(name = "idPeriodo")
    private Periodo periodo;

    @Id
    @Column(nullable = false, name = "idEmpresa")
    private Long idEmpresa;


}
