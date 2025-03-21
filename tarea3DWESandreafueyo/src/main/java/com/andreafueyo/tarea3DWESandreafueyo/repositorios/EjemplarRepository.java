package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;

import jakarta.transaction.Transactional;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>{
	
	@Query("SELECT Ejemplar e FROM Ejemplar e WHERE e.nombre = : nombre")
	List<Ejemplar> findEjemplaresByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT MAX(id) FROM Ejemplar e")
	Long findUltimoId();
	
    @Query("SELECT e FROM Ejemplar e WHERE e.planta.codigo IN :tipos")
    List<Ejemplar> buscarEjemplaresPorTipos(@Param("tipos") List<String> tipos);
    
    @Query("SELECT e FROM Ejemplar e WHERE e.planta.codigo = :codigo")
	List<Ejemplar> findEjemplaresByCodPlanta(@Param("codigo") String codigo);
    
    @Query("SELECT COUNT(e) FROM Ejemplar e WHERE e.nombre LIKE CONCAT(:nombre, '_%')")
	Long contarEjemplaresNombre(@Param("nombre") String nombre);
	
	@Transactional
	@Modifying
	@Query("UPDATE Ejemplar e SET e.nombre = :nombre WHERE e.id = :id")
	int actualizarNombreEjemplar(@Param("id") Long id, @Param("nombre") String nombre);
	
	default Long ultimoIdEjemplarByPlanta(Planta p) {
		List<Ejemplar> lista = findAll();
			if(!lista.isEmpty()) {
				long ret = 0;
				for (Ejemplar e : lista)
					if (e.getPlanta().getId().equals(p.getId()))
						ret++;
				return ret;
			}
			
			return 0L;
	}
	
	default List<Ejemplar> todosEjemplaresDescendiente() {
		return findAll((Sort.by(Sort.Direction.DESC, "id")));
	}

}
