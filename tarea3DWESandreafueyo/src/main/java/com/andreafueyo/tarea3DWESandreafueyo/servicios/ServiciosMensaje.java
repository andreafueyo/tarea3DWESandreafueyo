package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EjemplarRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.MensajeRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PersonaRepository;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;

@Service
public class ServiciosMensaje {
	
	@Autowired
	MensajeRepository mensajerepo;
	@Autowired
	PersonaRepository personarepo;
	@Autowired
	EjemplarRepository ejemplarrepo;

	public Mensaje insertar(Mensaje m) {
		return mensajerepo.saveAndFlush(m);
	}
	
//	public List<Mensaje> findByTipo(String tipo) { 
//		return mensajerepo.findByTipo(tipo);
//	}

	public List<Mensaje> findByEjemplar(Long idEjemplar) { 
		return mensajerepo.findByEjemplar(idEjemplar);
	}

	public List<Mensaje> findAll() { 
		return mensajerepo.findAll();
	}
		
	public void registrarMensaje(Long id_ej, Long id_persona, String mensaje) {
		Mensaje m = new Mensaje();
		Persona persona = personarepo.findByPersonaId(id_persona);
		Ejemplar ejemplar = ejemplarrepo.findEjemplaresById(id_ej);
		m.setFechahora(LocalDateTime.now());
		m.setMensaje(mensaje);
		m.setPersona(persona);
		m.setEjemplar(ejemplar);
		this.insertar(m);
	}
}