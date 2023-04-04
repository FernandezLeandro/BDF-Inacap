package com.bdf.inacap.domain.entity;

import com.bdf.inacap.utils.FormateadorDeFechas;
import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300, name = "descripcion")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaAlta")
    private Calendar fechaAlta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Transient
    private String fechaAltaString;


    public String getFechaAltaString() {
        String fecha = "";
        DateFormat sdf = FormateadorDeFechas.getDateTimeFormater("es");
        if (this.fechaAlta != null) {
            fecha = sdf.format(new Date(this.fechaAlta.getTimeInMillis()));
        }
        return fecha;
    }

    public void setFechaAltaString(String fecha) throws ParseException {
        if (fecha == null || fecha.isEmpty())
            setFechaAlta(null);
        else {
            DateFormat sdf = FormateadorDeFechas.getDateTimeFormater("es");
            Date date;
            date = sdf.parse(fecha);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            setFechaAlta(cal);
        }
    }
}
