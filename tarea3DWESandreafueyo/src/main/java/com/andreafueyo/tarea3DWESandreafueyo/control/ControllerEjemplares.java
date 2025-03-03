package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaPrincipal;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EjemplarRepository;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerEjemplares {
    
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

    @GetMapping("/gestionejemplares")
    public String gestionEjemplares(@RequestParam(value = "origen", required = false, defaultValue = "menuadmin") String origen, Model model) {
        model.addAttribute("origen", origen);  
        return "gestionejemplares"; 
    }

    /*Registrar ejemplar*/
    @GetMapping("/registrarejemplar")
    public String mostrarRegistroEjemplar(Model model) {
        model.addAttribute("plantas", servPlanta.verPlantas());
        return "registrarejemplar"; 
    }

    @PostMapping("/registrarejemplar")
    public String registrarEjemplar(@RequestParam("codPlanta") String codPlanta,
                                    @RequestParam("mensaje") String mensaje,
                                    Model model) {
    
        Planta planta = servPlanta.findByCod(codPlanta);
        if (planta == null) {
        	model.addAttribute("error", "No existe una planta con ese código.");
            model.addAttribute("plantas", servPlanta.verPlantas());
            return "registrarejemplar"; 
        }
        Long idPersona = portal.getCredencial().getPersona().getId();

        servEjemplar.registrarEjemplar(planta, idPersona, mensaje);
        model.addAttribute("mensajeExito", "¡Ejemplar insertado correctamente!");

        return "/registrarejemplar"; 
    }
    
    /* Filtrar ejemplares */
    @GetMapping("/filtrarejemplares")
    public String mostrarFiltrarEjemplares(Model model) {
        return "filtrarejemplares"; 
    }
    
    @PostMapping("/ejemplarfiltrartipo")
    public String ejemplarFiltrarTipo(@RequestParam("tipos") String tipos,
                                    Model model) {
    	
    	List<String> lTipos = Arrays.asList(tipos.split("\\s*,\\s*"));
    	List<Ejemplar> lEjemplar = ejemplarrepo.buscarEjemplaresPorTipos(lTipos);
    	if(lEjemplar == null || lEjemplar.isEmpty()) {
        	model.addAttribute("error", "No existen ejemplares con esos tipos.");
            return "filtrarejemplares"; 
    	}
    	
        model.addAttribute("ejemplares", lEjemplar);

        return "/filtrarejemplares"; 
    }
    
    
    /*Ver mensajes del ejemplar*/
    @GetMapping("/vermensajesejemplar")
    public String mostrarListaEjemplares(Model model) {
        model.addAttribute("ejemplares", servEjemplar.findAll());
        return "vermensajesejemplar"; 
    }

    @PostMapping("/vermensajesejemplar")
    public String MostrarListaMensajes(@RequestParam("ejemplarId") Long ejemplarId,
                                    Model model) {
    	
    	List<Mensaje> listaMensajes = servMensaje.findByEjemplar(ejemplarId);
    	
    	if(listaMensajes == null || listaMensajes.isEmpty()) {
        	model.addAttribute("error", "No existe mensajes con ese id de ejemplar.");
            model.addAttribute("ejemplares", servEjemplar.findAll());
            return "vermensajesejemplar"; 
    	}
    	
        model.addAttribute("mensajes", listaMensajes);
        return "/vermensajesejemplar";

    }
    
    
    
    
}
