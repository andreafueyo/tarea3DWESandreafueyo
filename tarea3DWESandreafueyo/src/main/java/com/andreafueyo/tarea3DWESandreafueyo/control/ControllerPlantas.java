package com.andreafueyo.tarea3DWESandreafueyo.control;

import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;


@Controller
public class ControllerPlantas {

    @Autowired
    private ServiciosPlanta servPlanta;

    /* Gestión de Plantas */
    @GetMapping("/gestionplantas")
    public String gestionPlantas(Model model) {
        List<Planta> listaPlantas = servPlanta.verPlantas();
        model.addAttribute("listaPlantas", listaPlantas); 
        return "gestionplantas"; 
    }

    /* Registrar Planta */
    @GetMapping("/registrarplanta")
    public String mostrarFormularioRegistroPlanta(Model model) {
    	model.addAttribute("planta", new Planta());
    	return "registrarplanta"; 
    }

    @PostMapping("/registrarplanta")
    public String registrarPlanta(@ModelAttribute Planta planta, Model model) {
    	
    	if(planta.getCodigo() == null || planta.getCodigo().contains(" ") || planta.getCodigo().isEmpty()) {
            model.addAttribute("error", "El código de la planta está vacío o contiene espacios.");
    	}
    	else {
        	if (servPlanta.validarPlanta(planta)) {
                servPlanta.insertarPlanta(planta);
                model.addAttribute("exito", "Planta registrada con éxito.");
            } else {
                model.addAttribute("error", "El código de la planta ya existe o es inválido.");
            }
    	}
    
    return "registrarplanta";
    }  
    

    /* Modificar Planta */
    @GetMapping("/modificarplanta")
    public String mostrarFormularioModificarPlanta(@RequestParam(required = false) String codPlanta, Model model) {

        List<Planta> listaPlantas = servPlanta.verPlantas();
        model.addAttribute("listaPlantas", listaPlantas);

        return "modificarplanta";  
    }

    @PostMapping("/modificarplanta")
    public String modificarPlanta(@RequestParam String codPlanta, 
                                  @RequestParam String nombreComun, 
                                  @RequestParam String nombreCientifico, 
                                  Model model) {

        if (nombreComun.trim().isEmpty() || nombreCientifico.trim().isEmpty()) {
            model.addAttribute("error", "Todos los campos son obligatorios.");
            return "modificarplanta";
        }

        Planta planta = servPlanta.findByCod(codPlanta);
        if (planta == null) {
            model.addAttribute("error", "Planta no encontrada.");
            return "modificarplanta"; 
        }

        planta.setNombrecomun(nombreComun);
        planta.setNombrecientifico(nombreCientifico);
        
        try {
            servPlanta.modificar(planta);
            model.addAttribute("success", "Planta modificada correctamente.");
            List<Planta> listaPlantas = servPlanta.verPlantas();
            model.addAttribute("listaPlantas", listaPlantas);
        } catch (Exception e) {
            model.addAttribute("error", "Error al modificar la planta.");
        }


        return "modificarplanta";  
    }


}
