package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaPrincipal;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerMensajes {
    
	@Autowired
    @Lazy
	ViveroFachadaPrincipal portal;
	
    @Autowired
    private ServiciosEjemplar servEjemplar;
    
    @Autowired
    private ServiciosMensaje servMensaje;
    
    @Autowired
    private ServiciosPlanta servPlanta;
    
    @Autowired
    private MainController maincontroller;

    
    /*Gestión mensajes*/
    @GetMapping("/gestionmensajes")
    public String gestionMensajes(Model model) {
        model.addAttribute("origen", maincontroller.getMenuLogin());   
        return "gestionmensajes"; 
    }
    
    /*Registrar mensaje*/
    @GetMapping("/registrarmensaje")
    public String mostrarListaEjemplares(Model model) {
        model.addAttribute("ejemplares", servEjemplar.findAll());
        model.addAttribute("origen", maincontroller.getMenuLogin());
        return "registrarmensaje"; 
    }

    @PostMapping("/registrarmensaje")
    public String registrarEjemplar(@RequestParam("ejemplarId") Long ejemplarId, @RequestParam("mensaje") String mensaje, @RequestParam(value = "origen", required = false, defaultValue = "menuadmin") String origen,Model model) {
			
    	Ejemplar ejemplar = servEjemplar.findById(ejemplarId);
		if (ejemplar == null) {
		model.addAttribute("error", "No existe un ejemplar con ese id.");
		model.addAttribute("ejemplares", servEjemplar.findAll());
		model.addAttribute("origen", origen); 
		return "registrarmensaje"; 
	}

		Long idPersona = portal.getCredencial().getPersona().getId();
		servMensaje.registrarMensaje(ejemplarId, idPersona, mensaje);
		model.addAttribute("mensajeExito", "¡Mensaje insertado correctamente!");
		model.addAttribute("origen", origen); 
        return "registrarmensaje"; 
    }
    
    
    /*Filtrar mensajes*/
    @GetMapping("/filtrarmensajes")
    public String mostrarFiltrarMensajes(Model model) {
        model.addAttribute("origen", maincontroller.getMenuLogin());
        List<Planta> listaPlantas = servPlanta.verPlantas();
        model.addAttribute("plantas", listaPlantas); 

        return "filtrarmensajes";
    }

    
    @PostMapping("/mensajeFiltrarPersona")
    public String mensajeFiltrarPersona(@RequestParam("persona") String persona,
                                    Model model) {
		List<Mensaje> listaMensajes = servMensaje.findByNombrePersona(persona);
    	if(listaMensajes == null || listaMensajes.isEmpty()) {
        	model.addAttribute("error", "No existen mensajes con ese nombre de persona.");
            return "filtrarmensajes"; 
    	}
    	
        model.addAttribute("mensajes", listaMensajes);

        return "filtrarmensajes"; 
    }
    
    @PostMapping("/mensajeFiltrarFechas")
    public String mensajeFiltrarFechas(@RequestParam("fechaMin") String fechaMin,
    								@RequestParam("fechaMax") String fechaMax,
                                    Model model) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;

        String inputMin = fechaMin;
        String inputMax = fechaMax;
        try {
            fechaInicio = LocalDateTime.parse(inputMin, formatter);
            fechaFin = LocalDateTime.parse(inputMax, formatter);
        } catch (DateTimeParseException e) {
        	model.addAttribute("error", "Formato no válido.");
            return "filtrarmensajes"; 
        }
        
		List<Mensaje> listaMensajes =  servMensaje.findMensajesEntreFechas(fechaInicio, fechaFin);
	 	if(listaMensajes == null || listaMensajes.isEmpty()) {
        	model.addAttribute("error", "No hay mensajes entre esas fechas.");
            return "filtrarmensajes"; 
	 	} else {
	        model.addAttribute("mensajes", listaMensajes);
	 	}

        return "filtrarmensajes"; 
    }
    
    @PostMapping("/mensajeFiltrarTipoPlanta")
    public String mensajeFiltrarTipoPlanta(@RequestParam("codplanta") String codplanta,
                                    Model model) {
		List<Mensaje> listaMensajes = servMensaje.findByTipoPlanta(codplanta);
    	if(listaMensajes == null || listaMensajes.isEmpty()) {
        	model.addAttribute("error", "No existen mensajes con ese código de planta.");
            return "filtrarmensajes"; 
    	}
    	
        model.addAttribute("mensajes", listaMensajes);

        return "filtrarmensajes"; 
    }
    
}

