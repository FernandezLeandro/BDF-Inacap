package com.bdf.inacap.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Basic;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


@Entity
@Data
public class PlanDeDeudaLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    EventoLegal evento;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar fechaAprobacion;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar fechaCalculoIntereses;

    @Enumerated
    @Column(nullable = false)
    EstadoPlanDeDeuda estado;

    @Enumerated
    @Column(nullable = false)
    TipoDePlanDeDeuda tipo;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "plan")
    List<ItemPlanLegal> items;

    @Basic
    String observaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    Usuario fiscalizador;

    @ManyToOne(fetch = FetchType.EAGER)
    Usuario aprobador;

    @Transient
    Double montoDeDeudaTotal;

    @Embedded
    Contacto contacto;

    public PlanDeDeudaLegal(EventoLegal evento, Map<Periodo, List<BoletaEmitida>> boletas) {
        this();
        this.setEvento(evento);
        List<ItemPlanLegal> items = new ArrayList<ItemPlanLegal>();
        for (Periodo periodo : evento.getPeriodos()) {
            boolean estaExento = false;
            for (BoletaEmitida boleta : boletas.get(periodo)) {
                estaExento = estaExento || boleta.getEstado().equals(EstadoEnum.EMPLEADOS0);
            }
            if (!estaExento)
                items.add(new ItemPlanLegal(periodo, boletas.get(periodo)));
        }
        this.setItems(items);
        this.contacto = new Contacto();
    }

    public PlanDeDeudaLegal() {
        this.setEstado(EstadoPlanDeDeuda.PENDIENTE_APROBACION);
        this.setTipo(TipoDePlanDeDeuda.NORMAL);
        this.setFechaCalculoIntereses(Calendar.getInstance());
        this.setObservaciones("");
    }
}
