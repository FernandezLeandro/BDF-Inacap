package com.bdf.inacap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;


import org.springframework.ui.ModelMap;


public class Utils {
	
    public static String completarDosDecimales(Double valorBasico) {

        DecimalFormat df = new DecimalFormat("###,###,##0.00");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return df.format(valorBasico);

    }

    public static void exportToExcelOrPdf(ModelMap model,String tipoExt,Map parameters,
                                   JRBeanCollectionDataSource beanColDataSource,String nombreArchivo)
            throws FileNotFoundException, JRException, IOException {
        String generatedFileName = "";
        String sourceFileName ="";
        String outPutFileName = "";
        String generatedFilePath = ParameterService.getOutPutFilesPath();
        if(nombreArchivo.equalsIgnoreCase("reportes")){
            sourceFileName = ParameterService.getJrxmlRepotesPagosFile();
            outPutFileName = "reporteDePagos";
        }else{
            sourceFileName = ParameterService.getJrxmlAsignacionEmpresasFile();
            outPutFileName = "clientes";
        }

        File sourceFile = new File(sourceFileName);
        InputStream inputStream = new FileInputStream(sourceFile);
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

        if(tipoExt.equalsIgnoreCase("Excel")){
            generatedFileName = outPutFileName+ ".xls";
            model.put("contentType", "application/vnd.ms-excel");
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, generatedFilePath+generatedFileName);
            exporter.exportReport();
        }else{
            generatedFileName = outPutFileName+".pdf";
            model.put("contentType", "application/pdf");
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING.JASPER_PRINT,jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, generatedFilePath + generatedFileName);
            exporter.exportReport();
        }
        model.put("filename", generatedFileName);

        File file = new File(generatedFilePath+generatedFileName);
        InputStream is = new FileInputStream(file);
        model.put("data", IOUtils.toByteArray(is));

        is.close();
        file.delete();

    }

    public static String getNominalApagar(PlanPagoDTO dto) {
		try {
			// valor cuota * Empleados Auditados
			Integer auditados = 0;
			if (dto.getPlanDePago().getEmpleadosAuditado() != null)
				auditados = dto.getPlanDePago().getEmpleadosAuditado();

			Double nominal = dto.getValorCuota();

			Double total = nominal * auditados;
			dto.getPlanDePago().setNominalAPagar(total.doubleValue());
			total = total * 100;
			// Round to the nearest integer.
			long tmp = Math.round(total);
			double dbl = new Double(tmp);
			return Utils.completarDosDecimales(dbl / 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static double getMontoAPagar(PlanPagoDTO dto) {
		try {
			// valor cuota * (Empleados pagados - Empleados Auditados)
			Integer auditados = 0;
			if (dto.getPlanDePago().getEmpleadosAuditado() != null)
				auditados = dto.getPlanDePago().getEmpleadosAuditado();

			Integer pagados = 0;
			Double nominal = dto.getValorCuota();
			if (dto.getPlanDePago().getEmpleadosAbonado() != null) {
				pagados = dto.getPlanDePago().getEmpleadosAbonado().intValue();
			}
			dto.setMontoAPagar(nominal * (auditados - pagados));
			return nominal * (auditados - pagados);

		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String getDiasMora(PlanPagoDTO dto) {
		try {
			Calendar fechaVencim = null;
			if (dto.getPlanDePago().getPeriodo() != null){
				fechaVencim = dto.getPlanDePago().getPeriodo().getVencimiento();
			}
			
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(dto.getPlanDePago().getFechaCalcIntereses());

			long fechaInicialMs = fechaVencim.getTimeInMillis();
			long fechaFinalMs = cal.getTimeInMillis();
			long diferencia = fechaFinalMs - fechaInicialMs;
			
			Integer dias = 0;
			if (diferencia > 0 ){
				dias = Math.round(diferencia / (1000 * 60 * 60 * 24));
			}
			
			return dias.toString();
		} catch (Exception e) {
			return "0";
		}

	}
	
	public static String getInteres2Porciento(PlanPagoDTO dto, Double interesesFinanciamiento, List<CotizacionInteres> cotizacionesIntereses) {
		// si el aprobador esta seleccionado, entocnes no hay interes
		if (dto.getPlanDePago().getActa().getAprobadorIntereses() != null) {
			dto.getPlanDePago().setIntereses(0D);
			return "0";
		}

		long diasMora = StringUtils.isEmpty(getDiasMora(dto))?0:new Long(getDiasMora(dto));
		if (diasMora < 0 || diasMora == 0){
			dto.getPlanDePago().setIntereses(0D);
			return "0";
		}
		
		Double montoAPagar = dto.getMontoAPagar();
		Double interesTotal = 0D;

		Calendar fechaCalcInteres = GregorianCalendar.getInstance();
		Calendar fechaPago = GregorianCalendar.getInstance();
		fechaCalcInteres.setTime(dto.getPlanDePago().getFechaCalcIntereses());
		if (dto.getPlanDePago().getFechaPago() != null)
			fechaPago.setTime(dto.getPlanDePago().getFechaPago());
		else
			fechaPago.setTime(dto.getPlanDePago().getFechaCalcIntereses());

		Double valorPrevio = 0D;
		for (CotizacionInteres cotizacionInteres : cotizacionesIntereses) {

			if (cotizacionInteres.getDesde().after(Calendar.getInstance())) {
				cotizacionInteres.setPorcentaje(valorPrevio);
			}
			valorPrevio = cotizacionInteres.getPorcentaje();
		}

		// EZE: Acá es donde hay que tocar por el tema del cálculo de
		// intereses por financiación.

		CotizacionInteres interesFinanciamiento = new CotizacionInteres(fechaCalcInteres, null, interesesFinanciamiento);

		interesTotal = cotizar1erPago(cotizacionesIntereses, fechaCalcInteres, dto.getPlanDePago().getPeriodo().getVencimiento(), montoAPagar);
		if (dto.getPlanDePago().getActa()!=null && dto.getPlanDePago().getActa().getTieneInteresFinanciacion()!=null && dto.getPlanDePago().getActa().getTieneInteresFinanciacion())
			interesTotal += interesFinanciamiento.cotizar(fechaPago, fechaCalcInteres) * montoAPagar;

		if (interesTotal < 0D)
			interesTotal = 0D;
		dto.getPlanDePago().setIntereses(interesTotal);
		interesTotal = interesTotal * 100;
		// Round to the nearest integer.
		long tmp = Math.round(interesTotal);
		double dbl = new Double(tmp);
		return Utils.completarDosDecimales(dbl / 100);
	}
	
	public static Double cotizar1erPago(List<CotizacionInteres> cotizacionesIntereses, Calendar fechaCalcInteres, Calendar fechaVencimiento, Double valorBasico) {
		Double interes = 0d;
		for (CotizacionInteres cotizacion : cotizacionesIntereses) {
			interes += cotizacion.cotizar(fechaCalcInteres, fechaVencimiento);
		}
		interes *= valorBasico;
		return interes;
	}
	
}
