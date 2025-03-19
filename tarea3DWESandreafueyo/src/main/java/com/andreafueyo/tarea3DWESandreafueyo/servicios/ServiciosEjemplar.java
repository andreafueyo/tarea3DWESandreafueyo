package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.CarritoCompra;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Cliente;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Pedido;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.ClienteRepository;
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
	@Autowired
	private ClienteRepository clienterepo;
	@Autowired
	private ServiciosPlanta servPlanta;
	@Autowired
	private ServiciosMensaje servMensaje;
	@Autowired
	private ServiciosPedido servPedido;
	

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

	public List<Ejemplar> findEjemplaresByCodPlanta(String codigo){
		return ejemplarrepo.findEjemplaresByCodPlanta(codigo);
	}
	
	public void actualizar(Ejemplar e) {
		ejemplarrepo.saveAndFlush(e);
	}

	public void insertar(Ejemplar e) {
		ejemplarrepo.save(e);
	}

	public List<Ejemplar> findAll() {
		return ejemplarrepo.findAll();
	}

	//Como es findById, deuvelve un Optional
	public Ejemplar findById(Long id) {
		Optional<Ejemplar> optEjemplar = ejemplarrepo.findById(id);
		if(optEjemplar.isEmpty()) {
			return null;
		}
		else {
			return optEjemplar.get();
		}
	}

	public Long findUltimoId() {
		return ejemplarrepo.findUltimoId();
	}

	public void registrarEjemplar(Planta pl, Long id_persona, String mensaje) {

		Long new_id = this.contarEjemplaresNombre(pl.getCodigo())+101;
		Ejemplar ej = new Ejemplar();
		//ej.setId(new_id);
		ej.setNombre(pl.getCodigo()+"_"+new_id);
		ej.setPlanta(pl);
		ej.setDisponible(true);
		this.actualizar(ej);	
		Mensaje m = new Mensaje();
		m.setFechahora(LocalDateTime.now());
		m.setMensaje(mensaje);

		Persona p = personarepo.findByPersonaId(id_persona);
		m.setPersona(p);
		m.setEjemplar(ej);

		mensajerepo.saveAndFlush(m);
	}

	public void buscarEjemplaresPorTipos(List<String> codigos) {
		List<Ejemplar> listaEjemplares = ejemplarrepo.buscarEjemplaresPorTipos(codigos);

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

	public void reservarEjemplares(Long pedidoId, Cliente cl, CarritoCompra carritoCompra) {
		List<Ejemplar> lEjemplaresReservar = new ArrayList<Ejemplar>();
		Map<String, Integer> items = carritoCompra.getItems();
		Pedido pedido = servPedido.findById(pedidoId);

		// Verificar disponibilidad de cada planta
		Iterator<Map.Entry<String, Integer>> iterator = items.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> item = iterator.next();
			String codigoPlanta = item.getKey();
			int cantidadSolicitada = item.getValue();

			Planta planta = servPlanta.findByCod(codigoPlanta);
			List<Ejemplar> lEjemplares = planta.getListaEjemplaresDisponibles();

			for(int i = 0; i < cantidadSolicitada; i++) {
				lEjemplares.get(i).setDisponible(false);
				lEjemplares.get(i).setPedido(pedido);
				lEjemplaresReservar.add(lEjemplares.get(i));
		        String mensaje = "Cliente " + cl.getCredenciales().getUsuario() + " compró el ejemplar "
		        		+ lEjemplares.get(i).getId() + " el día " + LocalDate.now() + " en el pedido "+pedidoId;
		        
		        servMensaje.registrarMensajeCliente(lEjemplares.get(i).getId(), cl.getId(), mensaje);
			}
		}
		ejemplarrepo.saveAll(lEjemplaresReservar);

	}
	
	
	public Long contarEjemplaresNombre(String nombre) {
		return ejemplarrepo.contarEjemplaresNombre(nombre);
	}

}