package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaPrincipal;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

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

    @GetMapping("/gestionejemplares")
    public String gestionEjemplares(@RequestParam(value = "origen", required = false, defaultValue = "menuadmin") String origen, Model model) {
        model.addAttribute("origen", origen);  
        return "gestionejemplares"; 
    }

    @GetMapping("/registrarejemplar")
    public String mostrarRegistroEjemplar(Model model) {
        model.addAttribute("plantas", servPlanta.verPlantas());
        return "registrarejemplar"; 
    }

    @PostMapping("/registrarejemplar")
    public String registrarEjemplar(@RequestParam("codPlanta") String codPlanta,
                                    @RequestParam("mensaje") String mensaje,
                                    Model model) {
        // Validar si la planta existe
        Planta planta = servPlanta.findByCod(codPlanta);
        if (planta == null) {
        	model.addAttribute("error", "No existe una planta con ese código.");
            model.addAttribute("plantas", servPlanta.verPlantas());
            return "registrarejemplar"; 
        }
        // Validar que ID Persona sea un número válido
        Long idPersona = portal.getCredencial().getPersona().getId();

        // Registrar el ejemplar
        servEjemplar.registrarEjemplar(planta, idPersona, mensaje);
        model.addAttribute("mensajeExito", "¡Ejemplar insertado correctamente!");

        return "/registrarejemplar"; 
    }
    
    @GetMapping("/vermensajesejemplar")
    public String mostrarListaEjemplares(Model model) {
        model.addAttribute("ejemplares", servEjemplar.findAll());
        return "vermensajesejemplar"; 
    }

    @PostMapping("/vermensajesejemplar")
    public String MostrarListaMensajes(@RequestParam("codPlanta") String codPlanta,
                                    @RequestParam("mensaje") String mensaje,
                                    Model model) {
        // Validar si la planta existe
        Planta planta = servPlanta.findByCod(codPlanta);
        if (planta == null) {
        	model.addAttribute("error", "No existe una planta con ese código.");
            model.addAttribute("plantas", servPlanta.verPlantas());
            return "registrarejemplar"; 
        }
        // Validar que ID Persona sea un número válido
        Long idPersona = portal.getCredencial().getPersona().getId();

        // Registrar el ejemplar
        servEjemplar.registrarEjemplar(planta, idPersona, mensaje);
        model.addAttribute("mensajeExito", "¡Ejemplar insertado correctamente!");

        return "/registrarejemplar"; 
    }
    
    
    
    
}
