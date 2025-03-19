package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.CarritoCompra;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.CustomUserDetailsServiceImpl;
import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaPrincipal;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPedido;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerPedido {

	@Autowired
	@Lazy
	ViveroFachadaPrincipal portal;

	@Autowired
	private ServiciosEjemplar servEjemplar;

	@Autowired
	private ServiciosPlanta servPlanta;

	@Autowired
	private ServiciosPedido servPedido;

	@Autowired
	private MainController maincontroller;

	@Autowired
	private CarritoCompra carritoCompra;

	@Autowired
	private CustomUserDetailsServiceImpl userDetails;

	@GetMapping("/realizarpedido")
	public String mostrarRealizarPedido(Model model) {
		model.addAttribute("plantas", servPlanta.verPlantas());
		model.addAttribute("origen", maincontroller.getMenuLogin());  
		return "realizarpedido"; 
	}
	
	@GetMapping("/gestionstock")
	public String mostrarGestionStock(Model model) {
		model.addAttribute("plantas", servPlanta.verPlantas());
		model.addAttribute("origen", maincontroller.getMenuLogin());  
		return "gestionstock"; 
	}
	
	@GetMapping("/verejemplaresplanta")
	public String mostrarVerEjemplaresPlanta(Model model) {
		return "gestionstock"; 
	}
	
	@PostMapping("/verejemplaresplanta")
	public String verEjemplaresPlanta(
			@RequestParam("codPlanta") String codPlanta,
			Model model) {

		model.addAttribute("origen", maincontroller.getMenuLogin()); 

		List<Ejemplar> lEjemplaresPlanta = servEjemplar.findEjemplaresByCodPlanta(codPlanta);
		if(lEjemplaresPlanta.isEmpty()) {
			model.addAttribute("error", "No hay ejemplares para esa planta");
		}
		else {
			model.addAttribute("ejemplares", lEjemplaresPlanta);
		}

		model.addAttribute("plantas", servPlanta.verPlantas());
		model.addAttribute("origen", maincontroller.getMenuLogin()); 
		return "gestionstock";
	}

	@PostMapping("/anadirpedido")
	public String anadirPedido(
			@RequestParam("codPlanta") String codPlanta,
			@RequestParam("cantidadSeleccionada") String cantidadSeleccionada,
			@RequestParam("ejemplaresDisponibles") int ejemplaresDisponibles,
			Model model) {

		int cantSeleccionada = 0;

		if(cantidadSeleccionada.isBlank()) {
			model.addAttribute("error", "No has seleccionado ninguna cantidad para esa planta");
			model.addAttribute("plantas", servPlanta.verPlantas());
			model.addAttribute("origen", maincontroller.getMenuLogin());  
			return "realizarpedido";

		} else{
			cantSeleccionada = Integer.parseInt(cantidadSeleccionada);
		}

		model.addAttribute("origen", maincontroller.getMenuLogin()); 


		if (cantSeleccionada > ejemplaresDisponibles) {
			model.addAttribute("error", "No puedes pedir una cantidad mayor a la disponible");
			model.addAttribute("plantas", servPlanta.verPlantas());
			model.addAttribute("origen", maincontroller.getMenuLogin());  
			return "realizarpedido";
		}
		if(cantSeleccionada == 0) {
			model.addAttribute("error", "No has seleccionado ninguna cantidad para esa planta");
			model.addAttribute("plantas", servPlanta.verPlantas());
			model.addAttribute("origen", maincontroller.getMenuLogin());  
			return "realizarpedido";

		}

		carritoCompra.agregarItem(codPlanta, cantSeleccionada);
		return "redirect:/vercarrito";
	}

	@GetMapping("/vercarrito")
	public String verCarrito(Model model) {
		model.addAttribute("carrito", carritoCompra.getItems());
		return "vercarrito";
	}

	@PostMapping("/eliminarpedido")
	public String eliminarPedido(@RequestParam("codigo") String codigo) {
		carritoCompra.eliminarItem(codigo);
		return "redirect:/vercarrito";
	}

	@PostMapping("/vaciarcarrito")
	public String vaciarCarrito() {
		carritoCompra.vaciarCarrito();
		return "redirect:/vercarrito";
	}

	@GetMapping("/finalizarpedido")
	public String mostrarFinalizarPedido(Model model) {
		//comprobar cada item del carrito si sigue estando disponible esa cantidad
		Map<String, Integer> items = carritoCompra.getItems();

		if (items.isEmpty()) {
			model.addAttribute("error", "El carrito está vacío.");
			return "finalizarpedido";
		}
		model.addAttribute("carrito", items);
		model.addAttribute("cliente", userDetails.getCredenciales().getCliente());
		model.addAttribute("origen", maincontroller.getMenuLogin());  
		return "finalizarpedido"; 
	}

	@PostMapping("/finalizarpedido")
	public String finalizarPedido(Model model, RedirectAttributes redirectAttributes) {

		//comprobar cada item del carrito si sigue estando disponible esa cantidad
		Map<String, Integer> items = carritoCompra.getItems();
		model.addAttribute("carrito", items);
		model.addAttribute("cliente", userDetails.getCredenciales().getCliente());

		Iterator<Map.Entry<String, Integer>> iterator = items.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> item = iterator.next();
			String codigoPlanta = item.getKey();
			int cantidadSolicitada = item.getValue();

			Planta planta = servPlanta.findByCod(codigoPlanta);
			if (planta == null) {
				redirectAttributes.addFlashAttribute("error", "Hay una planta del pedido que ya no existe.");
				carritoCompra.eliminarItem(codigoPlanta);
				return "redirect:/vercarrito";
			} else if(planta.getEjemplaresDisponibles() < cantidadSolicitada) {
				redirectAttributes.addFlashAttribute("error", "La planta "+planta.getCodigo() + " no tiene stock suficiente para atender tu solicitud");
				carritoCompra.eliminarItem(codigoPlanta);
				return "redirect:/vercarrito";
			}

		}
		Long pedidoId = servPedido.registrarPedido(userDetails.getCredenciales().getCliente());
		servEjemplar.reservarEjemplares(pedidoId, userDetails.getCredenciales().getCliente(),carritoCompra);

		carritoCompra.vaciarCarrito();

		model.addAttribute("mensajeExito", "Pedido registrado!");
		return "finalizarpedido"; 
	}    
}