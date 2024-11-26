package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;


@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{

//	public void insertarPlanta(Planta p) {
//		this.saveAndFlush(p);
//	}
	
}