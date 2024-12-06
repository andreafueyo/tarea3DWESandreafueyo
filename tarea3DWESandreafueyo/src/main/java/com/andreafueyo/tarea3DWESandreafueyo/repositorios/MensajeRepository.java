package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>{

//	@Query("SELECT m FROM Mensaje m INNER JOIN plantas ON m.fk_ejemplaresMensajes = plantas.codigo WHERE plantas.nombreComun =: tipo")
//	List<Mensaje> findByTipo(@Param("tipo") String tipo);
	
	@Query("SELECT m FROM Mensaje m INNER JOIN ejemplares ON mensajes.idejemplar = ejemplares.id WHERE ejemplares.id=: idejemplar")
	List<Mensaje> findByEjemplar(@Param("idejemplar") Long idejemplar);
	
}
