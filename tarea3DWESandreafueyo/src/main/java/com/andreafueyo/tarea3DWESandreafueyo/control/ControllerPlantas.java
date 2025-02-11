package com.andreafueyo.tarea3DWESandreafueyo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

@Controller
public class ControllerPlantas {

    @Autowired
    private ServiciosPlanta servPlanta;
    
    /*Gestión plantas*/
    @GetMapping("/gestionplantas")
    public String gestionPlantas(Model model) {
        return "gestionplantas"; 
    }
    
    /*Registrar planta*/
    @GetMapping("/registrarplanta")
    public String mostrarFormularioRegistroPlanta(Model model) {
        return "registrarplanta"; 
    }
    
    @PostMapping("/registrarplanta")
    public String registrarPlanta(@RequestParam String codigo, 
                                  @RequestParam String nom_com, 
                                  @RequestParam String nom_cien, 
                                  Model model) {
        Planta planta = new Planta();
        planta.setCodigo(codigo);
        planta.setNombrecomun(nom_com);
        planta.setNombrecientifico(nom_cien);
        
        if (servPlanta.validarPlanta(planta)) {
            servPlanta.insertarPlanta(planta);
            model.addAttribute("mensaje", "Planta registrada con éxito");
        } else {
            model.addAttribute("error", "El código de la planta ya existe o es inválido");
        }
        
        return "redirect:/gestionplantas"; 
    }

    /*Modificar planta*/
    @GetMapping("/modificarplanta")
    public String mostrarFormularioModificarPlanta(@RequestParam String codPlanta, Model model) {
        Planta planta = servPlanta.findByCod(codPlanta);

        if (planta == null) {
            model.addAttribute("error", "Planta no encontrada con el código proporcionado.");
            return "gestionplantas";  
        }

        model.addAttribute("planta", planta);
        return "modificarplanta";  
    }

    @PostMapping("/modificarplanta")
    public String modificarPlanta(@RequestParam String codPlanta, 
                                  @RequestParam String nombreComun, 
                                  @RequestParam String nombreCientifico, 
                                  Model model) {
        Planta planta = servPlanta.findByCod(codPlanta);

        if (planta == null) {
            model.addAttribute("error", "Planta no encontrada.");
            return "gestionplantas";  
        }

        planta.setNombrecomun(nombreComun);
        planta.setNombrecientifico(nombreCientifico);
        servPlanta.modificar(planta);

        model.addAttribute("success", "Planta modificada correctamente.");
        return "redirect:/gestionplantas";  
    }
}
