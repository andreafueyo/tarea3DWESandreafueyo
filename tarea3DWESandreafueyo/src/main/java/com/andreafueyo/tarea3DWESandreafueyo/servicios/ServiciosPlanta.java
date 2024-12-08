package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PlantaRepository;
 
@Service
public class ServiciosPlanta {

	@Autowired
	private PlantaRepository plantarepo;
	
	public boolean validarPlanta(Planta p) {
		if (plantarepo.existeCodigo(p)) {
			return false;
		}
		return true;
	}
	
	
	public void insertarPlanta(Planta p) {
		Planta planta =  plantarepo.saveAndFlush(p);
	}
	
	public Planta modificar(Planta p) {
		return plantarepo.saveAndFlush(p);
	}

	public Planta findByCod(String cod) {
		return plantarepo.findByCod(cod);
	}
	
	public List<Planta> verPlantas() {
		return plantarepo.findAllByOrderByNombrecientificoAsc();
	}
}
