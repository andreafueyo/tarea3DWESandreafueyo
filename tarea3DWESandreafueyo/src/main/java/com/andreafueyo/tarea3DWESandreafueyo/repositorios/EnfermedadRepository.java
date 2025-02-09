package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Enfermedad;

public interface EnfermedadRepository  extends JpaRepository<Enfermedad, Long>{
	@Query("SELECT e FROM Enfermedad e WHERE e.nombre = :nombre")
	Enfermedad findByNombre(@Param("nombre") String nombre);
		

}
