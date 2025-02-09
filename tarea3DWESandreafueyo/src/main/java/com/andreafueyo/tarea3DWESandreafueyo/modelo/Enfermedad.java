package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="enfermedades")
public class Enfermedad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	/*autoincrement*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (unique = true)
	private String nombre;
	
	@Column 
	private String sintomas;
	
	@Column
	private boolean nociva;
	
	@ManyToMany(mappedBy = "enfermedades")
	private List<Planta> plantas = new ArrayList<>();
	    
	@OneToMany(mappedBy = "enfermedad", cascade = CascadeType.ALL)
	private List<Parasito> parasitos = new LinkedList<Parasito>();


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


	public String getSintomas() {
		return sintomas;
	}


	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}


	public boolean isNociva() {
		return nociva;
	}


	public void setNociva(boolean nociva) {
		this.nociva = nociva;
	}


	public List<Planta> getPlantas() {
		return plantas;
	}


	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}


	public List<Parasito> getParasitos() {
		return parasitos;
	}


	public void setParasitos(List<Parasito> parasitos) {
		this.parasitos = parasitos;
	}


	@Override
	public String toString() {
		String ret = "";
		ret += "---ENFERMEDAD---";
		ret += "ID: " + this.id;
		ret += "\nNombre: " + this.nombre;
		ret += "\nSíntomas: " + this.sintomas;
		ret += "\nNociva: " + this.nociva;
		ret += "\nPlantas: " + this.plantas;
		ret += "\nParásitos: " + this.parasitos;
		return ret;
	}
	

}
