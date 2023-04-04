package com.bdf.inacap.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name="ChequeEnCartera")
public class ChequeEnCartera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Cheque cheque;

	//Banco de origen del cheque. 
	@ManyToOne
	private Cuenta bancoPago;
	
	//Banco donde se deposita el cheque.
	@ManyToOne
	private Banco bancoDeposito;
	
	@Basic
	private String nroCheque;
	
	//Fecha en la que el cheque es recibido en la cartera.
	@Temporal(TemporalType.DATE)
	private Date fechaRecepcion;
	
	//Fecha en la que se emitio el cheque. Dato del cheque fisico.
	@Temporal(TemporalType.DATE)
	private Date fechaEmision;
	
	//Fecha en la que se acredita el cheque. Dato del cheque fisico.
	@Temporal(TemporalType.DATE)
	private Date fechaAcreditacion;
	
	//Fecha en la que se depositó el cheque.
	@Temporal(TemporalType.DATE)
	private Date fechaDeposito;
	
	//Fecha en la que se confirmo como aceptado o rechazado el cheque.
	@Temporal(TemporalType.DATE)
	private Date fechaConfirmacion;
	
	@Enumerated
	private EstadoCheque estado;
	
	//Detalle el algún cambio.
	@Basic
	private String observaciones;
	
	//Estado con el que proviene del acta. Si hubo cambios o no. 
	@Basic 
	private Observacion estadoObservacion;
	
	@Basic
	private BigDecimal importe;
	
	//Empresa a la que corresponde el cheque. 
	@ManyToOne
	private Empresa empresa;
	
	//Cheque al que reemplaza. 
	@OneToOne
	private ChequeEnCartera reemplazado;
	
	@Basic
	@Column(nullable=false)
	private Boolean borrado;


	public ChequeEnCartera(Cheque cheque) {
		super();
		this.setCheque(cheque);
		this.setBancoDeposito(cheque.getBancoDeposito()) ;
		this.setNroCheque(cheque.getNroCheque());
		this.setFechaRecepcion(new Date());
		/*** No se permite completar la fecha de emision en la aplicacion de actas
		 *** por eso ingresamos la fecha de acreditacion como fecha de emision.
		 ***/
		this.setFechaEmision(cheque.getFechaAcreditacion());
		this.setFechaAcreditacion(cheque.getFechaAcreditacion());
		this.setObservaciones(cheque.getObservaciones());
		this.setImporte(new BigDecimal(cheque.getImporte().doubleValue()));
		this.setEmpresa(cheque.getEmpresa());
		this.borrado = false;
		this.estadoObservacion = Observacion.NO_OBSERVADO;
		this.estado = EstadoCheque.EN_CARTERA;
	}

	public ChequeEnCartera() {
		super();
		this.estadoObservacion = Observacion.NO_OBSERVADO;
		this.estado = EstadoCheque.EN_CARTERA;
		this.borrado = false;
	}

	public ChequeEnCartera(ChequeEnCartera reemplazado) {
		this.setReemplazado(reemplazado);
		this.setFechaRecepcion(new Date());
		if(reemplazado.getImporte()!=null){
			this.setImporte(reemplazado.getImporte());
		}
		if(reemplazado.getEmpresa()!=null){
			this.setEmpresa(reemplazado.getEmpresa()) ;
		}
		if(reemplazado.getCheque()!=null){
			this.setCheque(reemplazado.getCheque());
		}
		this.estadoObservacion = Observacion.NO_OBSERVADO;
		this.estado = EstadoCheque.EN_CARTERA;
		this.borrado = false;
	}

	
}