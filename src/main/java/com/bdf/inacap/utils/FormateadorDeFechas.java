package com.bdf.inacap.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormateadorDeFechas {

    public static Date parseFecha(String date, String idioma){

        String[] strs = date.split("/");
        Calendar cal = Calendar.getInstance();
        if ( idioma.equals("en") ){
            //year month date
            cal.set(Integer.parseInt((strs)[2]),Integer.parseInt((strs)[0]) - 1,Integer.parseInt((strs)[1]), 0,0,0);
        }else{
            cal.set(Integer.parseInt((strs)[2]),Integer.parseInt((strs)[1]) - 1,Integer.parseInt((strs)[0]),0,0,0);
        }

        return new Date(cal.getTime().getTime());
    }

    /**
     * Si alguna de las dos fecha de inicio o fin fue inicializada, devuelve fijandose en la variable booleana
     * para saber a cual de las dos fechas corresponde, la fecha correspondiente. En el caso de esta misma
     * haber sido ingresada, la devuelve, sino la setea como la fecha de inicio(en el caso de desde) o fecha
     * actual(en el caso de hasta).
     *
     * @param fechaDesde
     * @param fechaHasta
     * @param desde
     * @param currentLocale
     * @return
     * @throws ParseException
     */
    public static Date asignarFecha(String fechaDesde,String fechaHasta, boolean desde, Locale currentLocale) throws ParseException{

        Date fecha = null;

        if (!fechaDesde.equals("") || !fechaHasta.equals("")){
            if(desde){
                fecha = new Date(0);
                if (!fechaDesde.equals(""))
                    fecha.setTime(FormateadorDeFechas.getDateFormater(currentLocale.getLanguage()).parse(fechaDesde).getTime());
            }else{
                fecha = new Date(System.currentTimeMillis());
                if (!fechaHasta.equals(""))
                    fecha.setTime(FormateadorDeFechas.getDateFormater(currentLocale.getLanguage()).parse(fechaHasta).getTime());
            }
        }
        return fecha;
    }

    /**
     * Formateador de fechas.
     * "dd/MM/yyyy"
     * @return Returns the dateFormater.
     */
    public static DateFormat getDateFormater(String idioma) {
        if (idioma.equals("es")){
            return new SimpleDateFormat( "dd/MM/yyyy" );
        }else if(idioma.equals("en")){
            return new SimpleDateFormat( "MM/dd/yyyy" );
        }else{
            return new SimpleDateFormat( "dd/MM/yyyy" );
        }
    }

    public static DateFormat getDateFormater(String idioma, Locale loc) {
        if (idioma.equals("es")){
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy", loc);
            sdf.setLenient(false);
            return sdf;
        }else if(idioma.equals("en")){
            SimpleDateFormat sdf = new SimpleDateFormat( "MM-dd-yyyy", loc);
            sdf.setLenient(false);
            return sdf;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy", loc);
            sdf.setLenient(false);
            return sdf;
        }
    }


    /**
     * Metodo que devuelve el formato de fecha segun el idioma especificado.
     *
     * @return Returns the dateFormater.
     */
    public static DateFormat getDateFormat(String idioma) {
        if (idioma.equals("es")) {
            return new SimpleDateFormat("dd/MM/yy");
        } else if (idioma.equals(Locale.ENGLISH)) {
            return new SimpleDateFormat("MM/dd/yy");
        } else {
            return new SimpleDateFormat("dd/MM/yy");
        }
    }

    /**
     * Formateador de fechas.
     * "dd/MM/yyyy"
     * @return Returns the dateFormater.
     */

    public static DateFormat getMonthYearFormater(String idioma, Locale loc) {
        SimpleDateFormat sdf = new SimpleDateFormat( "MM-yyyy", loc);
        sdf.setLenient(false);
        return sdf;
    }
    public static DateFormat getDateFormaterDB() {
        return new SimpleDateFormat( "dd/MM/yyyy" );
    }

    public static DateFormat getDateFormaterMySQL() {
        return new SimpleDateFormat( "yyyy/MM/dd" );
    }

    public static DateFormat getFullDateFormater(String idioma, Locale loc) {

        if (idioma.equals("es")){
            return new SimpleDateFormat( "EEEEE d ' de ' MMMMM ' de 'yyyy", loc);
        } else if(idioma.equals("en")) {
            return new SimpleDateFormat( "EEE, MMMMM d, yyyy", loc);
        } else {
            return new SimpleDateFormat( "EEE d ' do ' MMMMM ' do 'yyyy", loc);
        }
    }



    public static DateFormat getDateTimeFormater(String idioma) {
        if (idioma.equals("es")){
            return new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
        }else if(idioma.equals("en")){
            return new SimpleDateFormat( "MM/dd/yyyy HH:mm:ss" );
        }else{
            return new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
        }
    }
    /**
     * Formateador de fechas con el mes en tres letras.
     * Con el locale por default
     * "dd/MMM/yyyy"
     * @return Returns the dateFormaterMonthText.
     */
    public static DateFormat getDateFormaterMonthText() {
        return new SimpleDateFormat( "dd/MMM/yyyy" );
    }

    /**
     * Formateador de fechas con el mes en tres letras.
     * Con el locale dado.
     * "dd/MMM/yyyy"
     * @return Returns the dateFormaterMonthText.
     */
    public static DateFormat getDateFormaterMonthText(Locale locale) {
        return new SimpleDateFormat( "dd/MMM/yyyy", locale );
    }

    /**
     * Formateador de fechas con horas.
     * "dd/MM/yyyy HH:mm"
     * @return Returns the dateTimeFormater.
     */
    public static DateFormat getDateTimeFormater() {
        return new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
    }

    /**
     * Formateador de fechas con horas con el mes en tres letras.
     * Con el locale dado.
     * "dd/MMM/yyyy HH:mm"
     * @return Returns the dateTimeFormaterMonthText.
     */
    public static DateFormat getDateTimeFormaterMonthText(Locale locale) {
        return new SimpleDateFormat( "dd/MMM/yyyy HH:mm", locale );
    }

    /**
     * Formateador de horas.
     * "HH:mm"
     * @return Returns the timeFormater.
     */
    public static DateFormat getTimeFormater() {
        return new SimpleDateFormat( "HH:mm" );
    }
    public static String getStringFecha(Date unaFecha) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
        String reportDate = df.format(unaFecha);
        return reportDate;
    }
}
