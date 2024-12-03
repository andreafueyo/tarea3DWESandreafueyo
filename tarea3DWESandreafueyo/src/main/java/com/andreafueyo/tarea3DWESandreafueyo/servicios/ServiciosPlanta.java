package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PlantaRepository;
 
@Service
public class ServiciosPlanta {

	@Autowired
	private PlantaRepository plantarepo;
	
	public boolean validarPlanta(Planta p) {
		if (plantarepo.existeCodigo(p))
			return false;
		return false;
	}
	
	
	public void insertarPlanta(Planta p) {
		plantarepo.saveAndFlush(p);
	}
}
