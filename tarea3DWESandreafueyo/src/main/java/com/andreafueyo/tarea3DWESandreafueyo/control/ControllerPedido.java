package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.CarritoCompra;
import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaPrincipal;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EjemplarRepository;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private ServiciosMensaje servMensaje;
    
    @Autowired
    private EjemplarRepository ejemplarrepo;
    
    @Autowired
    private MainController maincontroller;
    
    @Autowired
    private CarritoCompra carritoCompra;

    @GetMapping("/realizarpedido")
    public String mostrarRealizarPedido(Model model) {
    	model.addAttribute("plantas", servPlanta.verPlantas());
        model.addAttribute("origen", maincontroller.getMenuLogin());  
        return "realizarpedido"; 
    }

    @PostMapping("/anadirpedido")
    public String anadirPedido(
            @RequestParam("codPlanta") String codPlanta,
            @RequestParam("cantidadSeleccionada") int cantidadSeleccionada,
            @RequestParam("ejemplaresDisponibles") int ejemplaresDisponibles,
            Model model) {
    	   	
        model.addAttribute("origen", maincontroller.getMenuLogin()); 
    	

        if (cantidadSeleccionada > ejemplaresDisponibles) {
            model.addAttribute("error", "No puedes pedir una cantidad mayor a la disponible");
            return "realizarpedido";
        }
        
        if(cantidadSeleccionada == 0){
            model.addAttribute("error", "No has seleccionado ninguna cantidad para esa planta");
            return "realizarpedido";
        }

        carritoCompra.agregarItem(codPlanta, cantidadSeleccionada);
        return "redirect:/vercarrito";
    }

    @GetMapping("/vercarrito")
    public String verCarrito(Model model) {
    	System.out.println(carritoCompra);
        model.addAttribute("carrito", carritoCompra.getItems());
        return "vercarrito";
    }
    
    @GetMapping("/finalizarpedido")
    public String mostrarFinalizarPedido(Model model) {
        return "finalizarpedido";
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
    
    
}
