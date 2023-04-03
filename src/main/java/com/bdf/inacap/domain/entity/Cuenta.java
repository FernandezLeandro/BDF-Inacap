package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Cuenta")
public class Cuenta {

	@Basic
	@Column(unique = true, nullable = false)
	private String nombre;
	
	@Basic
	@Column(unique = true, nullable = false)
	private String numero;
	
	@ManyToOne
	private Banco banco;


}
