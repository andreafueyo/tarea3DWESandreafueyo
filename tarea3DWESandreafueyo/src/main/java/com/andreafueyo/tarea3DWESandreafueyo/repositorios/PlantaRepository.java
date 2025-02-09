package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;


@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{

    /**
     * Comprueba si existe una planta con el mismo código en la base de datos.
     * 
     * Este método obtiene la lista completa de plantas y la recorre para verificar 
     * si el código de la planta proporcionada coincide con alguno de los existentes.
     * 
     * @param p La planta cuya existencia se va a comprobar.
     * @return {@code true} si ya existe una planta con el mismo código; {@code false} en caso contrario.
     */
	
	default boolean existeCodigo(Planta p) {
		List<Planta> listaplantas = findAll();
		for(Planta aux:listaplantas) {
			if(aux.getCodigo() != null && p.getCodigo().equals(aux.getCodigo()))
					return true;
		}
		return false;
	}
	
	   /**
     * Busca una planta en la base de datos por su código.
     * 
     * Utiliza una consulta JPQL para encontrar una planta específica cuyo código 
     * coincida con el valor proporcionado.
     * 
     * @param cod El código de la planta a buscar.
     * @return La planta correspondiente al código proporcionado, o {@code null} si no se encuentra ninguna.
     */

	@Query("SELECT p FROM Planta p WHERE p.codigo = :cod")
	Planta findByCod(@Param("cod") String cod);
	
	
	/**
     * Obtiene una lista de todas las plantas ordenadas por su código de forma ascendente.
     * 
     * @return Una lista de plantas ordenadas ascendentemente por su código.
     */
	
	List<Planta> findAllByOrderByCodigoAsc();

			
}