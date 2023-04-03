package com.bdf.inacap.utils;

import com.bdf.inacap.domain.entity.Periodo;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static long obtenerDifferenciaMiliSegundos(Calendar fechaDesde, Calendar fechaHasta){
        return (long) (fechaHasta.getTimeInMillis() - fechaDesde.getTimeInMillis());
    }

    public static int obtenerDifferenciaSegundos(Calendar fechaDesde, Calendar fechaHasta){
        return (int) ((fechaHasta.getTimeInMillis() - fechaDesde.getTimeInMillis())/(1000));
    }

    public static int obtenerDifferenciaMinutos(Calendar fechaDesde, Calendar fechaHasta){
        return (int) ((fechaHasta.getTimeInMillis() - fechaDesde.getTimeInMillis())/(1000*60));
    }

    public static int obtenerDifferenciaMinutos(Date fechaDesde, Date fechaHasta){
        return (int) ((fechaHasta.getTime() - fechaDesde.getTime())/(1000*60));
    }

    public static int obtenerDifferenciaHoras(Calendar fechaDesde, Calendar fechaHasta){
        return (int) ((fechaHasta.getTimeInMillis() - fechaDesde.getTimeInMillis())/(1000*60*60));
    }

    public static String getFechaAsString(Calendar fecha, String idioma) {
        String fechaStr = "";
        if (fecha != null) {
            DateFormat sdf = FormateadorDeFechas.getDateFormater(idioma);
            fechaStr = sdf.format(new Date(fecha.getTimeInMillis()));
        }
        return fechaStr;
    }

    public static int obtenerDifferenciaDias(Calendar fechaDesde, Calendar fechaHasta){
        Date desde  = new Date();
        desde.setDate(fechaDesde.getTime().getDate());
        desde.setMonth(fechaDesde.getTime().getMonth());
        desde.setYear(fechaDesde.getTime().getYear());
        desde.setHours(0);
        desde.setMinutes(0);

        Date hasta = new Date();
        hasta.setDate(fechaHasta.getTime().getDate());
        hasta.setMonth(fechaHasta.getTime().getMonth());
        hasta.setYear(fechaHasta.getTime().getYear());
        hasta.setHours(0);
        hasta.setMinutes(0);

        return (int) ((hasta.getTime() - desde.getTime())/(1000 * 60 * 60 * 24));
    }

    public static Calendar getFecha(int dia, int mes, int anio){
        Calendar fecha = Calendar.getInstance();
        fecha.set(anio, mes -1, dia);
        return fecha;
    }

    public static boolean aplicaPeriodo(Periodo periodo, Calendar fechaInicio){
        Calendar fechaInicioActividad = (Calendar) fechaInicio.clone();
        if(fechaInicio!=null){
            fechaInicioActividad.set(Calendar.DAY_OF_MONTH,1);
        }else{
            fechaInicioActividad = Calendar.getInstance();
            fechaInicioActividad.set(2008, 06, 01,00,00,00);
        }
        int mes = Integer.parseInt(periodo.getPeriodo().substring(0,2));
        int anio = Integer.parseInt(periodo.getPeriodo().substring(3));
        Calendar comienzoPeriodo = Calendar.getInstance();
        comienzoPeriodo.set(anio,mes-1,01,0,0,0);
        return (obtenerDifferenciaDias(fechaInicioActividad,comienzoPeriodo) >= 0);

    }


}
