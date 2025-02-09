package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="parasitos")
public class Parasito implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(unique=true)
	private String nombre;
		
	@Column 
	private String color;
		
	@ManyToOne
	@JoinColumn(name="idenfermedad")
	private Enfermedad enfermedad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}


	@Override
	public String toString() {
		String ret = "";
		ret += "---PARÁSITO---";
		ret += "ID: " + this.id;
		ret += "\nNombre: " + this.nombre;
		ret += "\nColor: " + this.color;
		ret += "\nEnfermedad: " + this.enfermedad;
		return ret;
	}
}
