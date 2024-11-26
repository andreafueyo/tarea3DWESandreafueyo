package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="idplanta")
	private List<Ejemplar> ejemplaresEjemplars = new LinkedList<Ejemplar>();
	
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
}