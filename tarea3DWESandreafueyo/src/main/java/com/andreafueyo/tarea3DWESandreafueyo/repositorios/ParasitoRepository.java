package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Parasito;

@Repository
public interface ParasitoRepository extends JpaRepository<Parasito, Long>{

	List<Parasito> findAllByOrderByNombreAsc();
	
	@Query("SELECT p FROM Parasito p WHERE p.nombre = :nombre")
	Parasito findByNombre(@Param("nombre") String nombre);

}
