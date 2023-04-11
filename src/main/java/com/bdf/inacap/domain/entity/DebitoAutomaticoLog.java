package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "DebitoAutomaticoLog")
public class DebitoAutomaticoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DebitoAutomatico debitoAutomatico;

  //  @Temporal(TemporalType.DATE)
    private LocalDateTime fechaAcreditacion;

    @ManyToOne
    private Banco banco;

    private String cbu;

    //Fecha en la que el Banco informa que pudo cobrar
 //   @Temporal(TemporalType.DATE)
    private LocalDateTime fechaRecepcion;

    private Double importe;

    @Enumerated
    @Column(nullable = false)
    private EstadoDebitoAutomatico estado;

 //   @Temporal(TemporalType.DATE)
    private LocalDateTime fechaModificacion;

    @Column(nullable = false)
    private String usuario;


}
