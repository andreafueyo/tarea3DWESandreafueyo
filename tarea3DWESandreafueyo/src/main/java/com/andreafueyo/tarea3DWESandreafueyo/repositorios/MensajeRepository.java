package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import java.time.LocalDateTime;
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
	
	@Query("SELECT m FROM Mensaje m INNER JOIN m.ejemplar e WHERE e.id = :idejemplar")
	List<Mensaje> findByEjemplar(@Param("idejemplar") Long idejemplar);
	
	@Query("SELECT m FROM Mensaje m JOIN m.ejemplar e JOIN e.planta p WHERE p.codigo = :tipo")
	List<Mensaje> findByTipoPlanta(@Param("tipo") String tipo);
	
	@Query("SELECT m FROM Mensaje m JOIN m.persona p WHERE p.nombre = :nombre")
	List<Mensaje> findByNombrePersona(@Param("nombre") String nombre);
	
    @Query("SELECT m FROM Mensaje m WHERE m.fechahora BETWEEN :fechaInicio AND :fechaFin")
    List<Mensaje> findMensajesEntreFechas(@Param("fechaInicio") LocalDateTime fechaInicio, 
                                          @Param("fechaFin") LocalDateTime fechaFin);
	
}
