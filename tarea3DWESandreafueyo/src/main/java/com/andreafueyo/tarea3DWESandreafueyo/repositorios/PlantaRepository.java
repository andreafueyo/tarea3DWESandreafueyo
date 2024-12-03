package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;


@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{

	default boolean existeCodigo(Planta p) {
	List<Planta> listaplantas = findAll();
	for(Planta aux:listaplantas) {
		if(p.getCodigo().equals(aux.getCodigo()))
				return true;
	}
		return false;
	}
	
	@Modifying
	@Query("UPDATE Planta p SET p.nombreComun = :planta.nombreComun, p.nombreCientifico = :planta.nombreCientifico WHERE p.id = :planta.id")
	int modificar(@Param("planta") Planta p);

	
//	default Planta findByCod(String cod) {
//		
//	}
}