package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EjemplarRepository;

@Service
public class ServiciosEjemplar {
	
	@Autowired
	private EjemplarRepository ejemplarrepo;
	
	public List<Ejemplar> findEjemplaresByNombre(String nombre){
		return ejemplarrepo.findEjemplaresByNombre(nombre);
	}
	
	public int actualizarNombreEjemplar(Long id, String nombre) {
		return ejemplarrepo.actualizarNombreEjemplar(id, nombre);
	}
	
	public Long ultimoIdEjemplarByPlantLong(Planta p) {
	if(p!=null)
		return ejemplarrepo.ultimoIdEjemplarByPlanta(p);
	else 
		return null;
	}

	public void actualizar(Ejemplar e) {
		ejemplarrepo.saveAndFlush(e);
	}
	
}
