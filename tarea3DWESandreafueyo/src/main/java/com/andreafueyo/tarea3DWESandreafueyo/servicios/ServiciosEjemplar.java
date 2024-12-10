package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EjemplarRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.MensajeRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PersonaRepository;


@Service
public class ServiciosEjemplar {
	
	@Autowired
	private EjemplarRepository ejemplarrepo;
	@Autowired
	private MensajeRepository mensajerepo;
	@Autowired
	private PersonaRepository personarepo;
	
	public List<Ejemplar> findEjemplaresByNombre(String nombre){
		return ejemplarrepo.findEjemplaresByNombre(nombre);
	}
	
	public int actualizarNombreEjemplar(Long id, String nombre) {
		return ejemplarrepo.actualizarNombreEjemplar(id, nombre);
	}
	
	public Long ultimoIdEjemplarByPlantLong(Planta p) {
	if(p!=null)
		return ejemplarrepo.ultimoIdEjemplarByPlanta(p);
	else 
		return null;
	}

	public void actualizar(Ejemplar e) {
		ejemplarrepo.saveAndFlush(e);
	}

	public List<Ejemplar> findByTipo(String tipos) {
		return ejemplarrepo.findByTipo(tipos);
	}
	
	public List<Ejemplar> findAll() {
		return ejemplarrepo.findAll();
	}
	
	//Como es findById, deuvelve un Optional
	public Ejemplar findById(Long id) {
		Optional<Ejemplar> optEjemplar = ejemplarrepo.findById(id);
		return optEjemplar.get();
	}
	
	public Long findMaxId() {
		return ejemplarrepo.findMaxId();
	}
	
	public void registrarEjemplar(Planta pl, Long id_persona) {
		
		Long new_id = this.findMaxId()+1;
		Ejemplar ej = new Ejemplar();
		ej.setId(new_id);
		ej.setNombre(pl.getCodigo()+"_"+new_id);
		ej.setPlanta(pl);
		this.actualizar(ej);	
		
		Mensaje m = new Mensaje();
		m.setFechahora(LocalDateTime.now());
		m.setMensaje("mensaje");
		
		Persona p = personarepo.findByPersonaId(id_persona);
		m.setPersona(null);
		m.setEjemplar(ej);
		
		mensajerepo.saveAndFlush(m);
	}
	
	public void filtrarEjemplares(String codigos) {
		List<Ejemplar> listaEjemplares = this.findByTipo(codigos);
		
		for(Ejemplar e : listaEjemplares) {
			System.out.println();
			List<Mensaje> listaMensajes = mensajerepo.findByEjemplar(e.getId());
			int num_mensajes = listaMensajes.size();
			String ult_fecha = listaMensajes.get(0).getFechahora().toString();
			System.out.println("Ejemplar "+e.getNombre()+", Num Mensajes: "+num_mensajes+", ult mensaje: "+ult_fecha);
		}
	}
	
	public void mostrarEjemplares() {
		System.out.println();
		System.out.println("Estos son los ejemplares: ");
	 	List<Ejemplar> listaEjemplares = this.findAll();
		
		int contador = 1;
	 	for(Ejemplar e : listaEjemplares) {
	 		System.out.println(contador + ": " + e.toString());
	 		contador++;
	 	}
	 	System.out.println();
	}

	public void verMensajes(Long id_ej) {
		System.out.println();
		System.out.println("Estos son los mensajes para el ejemplar "+id_ej+": ");
		
		List<Mensaje> listaMensajes = mensajerepo.findByEjemplar(id_ej);
	 	if(listaMensajes == null || listaMensajes.isEmpty()) {
	 		System.out.println("No hay mensajes para este ejemplar");
	 	}else {
			for(Mensaje m : listaMensajes) {
		 		System.out.println(m.toString());
		 	}
		 	System.out.println();
	 	}
	}
	
}
