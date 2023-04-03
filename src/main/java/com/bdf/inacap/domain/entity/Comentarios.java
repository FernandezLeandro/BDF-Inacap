package com.bdf.inacap.domain.entity;

import com.migracionInacap.utils.FormateadorDeFechas;
import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ftavella on 29/01/2018.
 */
@Data
@Entity
@Table(name="Comentarios")
public class Comentarios{
    @Column(nullable = false, length = 300,name="descripcion")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaAlta")
    private Calendar fechaAlta;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @Transient
    private String fechaAltaString;


    public String getFechaAltaString() {
        String fecha = "";
        DateFormat sdf = FormateadorDeFechas.getDateTimeFormater("es");
        if(this.fechaAlta != null){
            fecha = sdf.format(new Date(this.fechaAlta.getTimeInMillis()));
        }
        return fecha;
    }

    public void setFechaAltaString(String fecha)throws ParseException {
        if(fecha == null || fecha.isEmpty())
            setFechaAlta(null);
        else{
            DateFormat sdf = FormateadorDeFechas.getDateTimeFormater("es");
            Date date;
            date = sdf.parse(fecha);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            setFechaAlta(cal);
        }
    }
}
