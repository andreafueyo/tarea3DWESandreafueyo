package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Parasito;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.ParasitoRepository;

@Service
public class ServiciosParasito {
	
	@Autowired
	private ParasitoRepository pararepo;
	
	public List<Parasito> mostrarParasitos(){
		return pararepo.findAllByOrderByNombreAsc();
	}

	public Parasito findByNombre(String nombre_parasito) {
		return pararepo.findByNombre(nombre_parasito);
	}
	
	public void insertarParasito(Parasito p) {
		pararepo.saveAndFlush(p);
	}

	public void modificarParasito(Parasito p) {
		pararepo.save(p);		
	}
}

