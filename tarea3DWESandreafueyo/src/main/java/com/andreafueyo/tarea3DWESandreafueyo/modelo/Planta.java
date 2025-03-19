package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="plantas")

public class Planta {

	@Id
	/*autoincrement*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String codigo;
	
	@Column
	private String nombrecomun;
	
	@Column
	private String nombrecientifico;
	
	 @ManyToMany
	 @JoinTable(
			 name = "planta_enfermedad",
	         joinColumns = @JoinColumn(name = "planta_id"),
	         inverseJoinColumns = @JoinColumn(name = "enfermedad_id")
	    )
	    private List<Enfermedad> enfermedades = new ArrayList<>();
		
		public List<Enfermedad> getEnfermedades() {
			return enfermedades;
		}

		public void setEnfermedades(List<Enfermedad> enfermedades) {
			this.enfermedades = enfermedades;
		}
	
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idplanta")
	private List<Ejemplar> ejemplares = new LinkedList<Ejemplar>();
	
	public Planta() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombrecomun() {
		return nombrecomun;
	}

	public void setNombrecomun(String nombrecomun) {
		this.nombrecomun = nombrecomun;
	}

	public String getNombrecientifico() {
		return nombrecientifico;
	}

	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}
	
    public List<Ejemplar> getListaEjemplaresDisponibles() {
    	List<Ejemplar> ejemplaresDisponibles = new ArrayList<Ejemplar>();
    	
    	for(Ejemplar e : ejemplares) {
    		if(e.isDisponible()) {
    			ejemplaresDisponibles.add(e);
    		}
    	}
        return ejemplaresDisponibles;
    }
	
    public int getEjemplaresDisponibles() {
    	return getListaEjemplaresDisponibles().size();
    }

	public void setEjemplaresEjemplars(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	
	@Override
	public String toString() {
		String ret = "";
		ret += "Codigo de planta: " + this.codigo;
		ret += "\nNombre científico: " + this.nombrecientifico;
		ret += "\nNombre común: " + this.nombrecomun;
		/** 
		 * me da error, tengo que mirarlo
		 * ret += "\nEnfermedades: " + this.enfermedades;
		 */
		return ret;
	}
	
	
	
	
}
