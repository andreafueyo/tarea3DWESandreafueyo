package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Enfermedad;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EnfermedadRepository;

@Service
public class ServiciosEnfermedad {

	@Autowired
	private EnfermedadRepository enfermedadrepo;

	public void insertarEnfermedad(Enfermedad enfermedad) {
		enfermedadrepo.saveAndFlush(enfermedad);
		
	}

	public Enfermedad findByNombre(String nombre_enfermedad) {
		return enfermedadrepo.findByNombre(nombre_enfermedad);
	}
	
}
