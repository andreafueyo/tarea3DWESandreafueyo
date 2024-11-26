package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{

	public void insertarPlanta(Planta p) {
		this.saveAndFlush(p);
	}
	
}