package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


@SuppressWarnings("serial")
@Entity
@Data
public class PlanDeDeudaLegal {

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

    @OneToMany(targetEntity=ItemPlanLegal.class, mappedBy="plan")
    List<ItemPlanLegal> items;

    @Basic
    String observaciones;

    @ManyToOne(fetch=FetchType.EAGER)
    Usuario fiscalizador;

    @ManyToOne(fetch=FetchType.EAGER)
    Usuario aprobador;

    @Transient
    Double montoDeDeudaTotal;

    @Embedded
    Contacto contacto;

    public PlanDeDeudaLegal(EventoLegal evento, Map<Periodo,List<BoletaEmitida>> boletas){
        this();
        this.setEvento(evento);
        List<ItemPlanLegal> items = new ArrayList<ItemPlanLegal>();
        for (Periodo periodo : evento.getPeriodos()) {
            boolean estaExento = false;
            for (BoletaEmitida boleta : boletas.get(periodo)) {
                estaExento = estaExento || boleta.getEstado().equals(EstadoEnum.EMPLEADOS0);
            }
            if(!estaExento)
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
