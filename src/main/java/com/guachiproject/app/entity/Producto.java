package com.guachiproject.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "producto")
public class Producto{
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@NotEmpty(message="*Campo Obligatorio, no debe estar vacio")
	@Column(length = 100)
	private String descripcion;
	private double precio;
	private int cantidad;
	//Atributos calculados
	
	
	private double valorCompra;
	
	private double total;
}
