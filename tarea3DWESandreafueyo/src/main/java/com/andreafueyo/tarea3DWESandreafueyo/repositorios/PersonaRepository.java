package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{

	@Query("SELECT p FROM Persona p WHERE p.email = : email")
	Persona findByEmail(@Param("email") String email);
	
	@Query("SELECT p FROM Persona p WHERE p.id = : id")
	Persona findByPersonaId(@Param("id") Long id);
	
	default boolean validarPersona(Persona p) { 
		//si ya existe persona con ese email devuelve false
		
		if(this.findByEmail(p.getEmail()) == null) {
			return true;
		}
		else {
			return false;
		}
	}


}
