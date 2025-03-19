package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Cliente;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Pedido;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.ClienteRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EjemplarRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.MensajeRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PedidoRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PersonaRepository;


@Service
public class ServiciosPedido {
	
	@Autowired
	private PedidoRepository pedidorepo;
	@Autowired
	private ClienteRepository clienterepo;
	

	public void actualizar(Pedido p) {
		pedidorepo.saveAndFlush(p);
	}
	
	public Pedido insertar(Pedido p) {
		return pedidorepo.save(p);
	}
		
	public Long registrarPedido(Cliente cl) {
		
	    // Asegurar que el Cliente está gestionado por la sesión actual
	    Cliente clientePersistente = clienterepo.findById(cl.getId())
	        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

	    Pedido p = new Pedido();
	    p.setCliente(clientePersistente); // Usar el cliente gestionado
	    p.setFecha(LocalDate.now());

	    return this.insertar(p).getId();

	}
	//Como es findById, deuvelve un Optional
	public Pedido findById(Long id) {
		Optional<Pedido> optPedido = pedidorepo.findById(id);
		if(optPedido.isEmpty()) {
			return null;
		}
		else {
			return optPedido.get();
		}
	}
}
