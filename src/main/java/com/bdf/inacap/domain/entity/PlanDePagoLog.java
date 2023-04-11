package com.bdf.inacap.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanDePagoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idPlanDePago;

    @ManyToOne
    private Acta acta;

    @ManyToOne
    private BoletaEmitida boleta;

    @ManyToOne
    private Periodo periodo;

    private Double periodoValor;

    private Integer empleadosAbonado;

    private Double importeAbonado;

    private Double interesesAbonado;

    private Integer empleadosAuditado;

    private Double nominalAPagar;

    private Double intereses;

    private LocalDateTime fechaCalcIntereses;

    private LocalDateTime fechaPago;

    @ManyToOne
    private Cheque cheque;

    @Column(nullable = false)
    private Boolean borrado;

    @Column(nullable = false, length = 50)
    private String usuarioUltimaModif;

    @Column(nullable = false)
    private LocalDateTime fechaUltimaModif;

}
