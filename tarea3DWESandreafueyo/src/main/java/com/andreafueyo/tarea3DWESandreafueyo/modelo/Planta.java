package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="plantas")

public class Planta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
