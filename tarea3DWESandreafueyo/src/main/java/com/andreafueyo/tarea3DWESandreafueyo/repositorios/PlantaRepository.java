package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;


@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{

	default boolean existeCodigo(Planta p) {
		List<Planta> listaplantas = findAll();
		for(Planta aux:listaplantas) {
			if(aux.getCodigo() != null && p.getCodigo().equals(aux.getCodigo()))
					return true;
		}
		return false;
	}
	
	@Query("SELECT p FROM Planta p WHERE p.codigo = :cod")
	Planta findByCod(@Param("cod") String cod);
	
	List<Planta> findAllByOrderByCodigoAsc();
			
}