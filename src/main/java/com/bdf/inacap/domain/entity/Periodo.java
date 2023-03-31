package com.bdf.inacap.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;



@SuppressWarnings("serial")
@Entity
@Data
public class Periodo extends Auditable{
    @Basic
    @Column(nullable = false, length = 7,name="periodo")
    private String periodo;

    @Column(nullable = false,name="vencimiento")
    @Temporal(TemporalType.DATE)
    private Calendar vencimiento;

    @Basic
    @Column(nullable = false,name="habilitadoEmision")
    private Boolean habilitadoEmision = true;

    public String getTextHabilitadoEmision() {
        String habilitado = "Sí";
        if(habilitadoEmision == false){
            habilitado= "No";
        }
        return habilitado;
    }
    public String getFechaVencimientoString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(getVencimiento().getTime());
    }


    @Override
    public String toString() {
        return this.getPeriodo();
    }

    public Periodo(Long id) {
        super();
        this.id = id;
    }

    public Periodo() {
        super();
    }

//	@Override
//	public boolean equals(Object other) {
//		return (this.periodo.equalsIgnoreCase(((Periodo) other).periodo));
//	}

    public static Periodo getById(Long id,List<Periodo> periodoList){
        for(Periodo per : periodoList){
            if(per.id.equals(id))
                return per;
        }
        return null;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + periodo.hashCode();
        return result;
    }

    //	@Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (!(other instanceof Periodo))
            return false;
        Periodo otherMyClass = (Periodo) other;
        if (!otherMyClass.getPeriodo().equalsIgnoreCase(this.getPeriodo()))
            return false;
        return true;
    }
}
