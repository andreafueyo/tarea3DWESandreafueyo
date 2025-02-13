package com.andreafueyo.tarea3DWESandreafueyo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("listaPlantas", listaPlantas); // Agregar la lista de plantas al modelo
        return "gestionplantas"; 
    }

    /* Registrar Planta */
    @GetMapping("/registrarplanta")
    public String mostrarFormularioRegistroPlanta(Model model) {
        return "registrarplanta"; 
    }

    @PostMapping("/registrarplanta")
    public String registrarPlanta(@RequestParam String codigo, 
                                  @RequestParam String nom_com, 
                                  @RequestParam String nom_cien, 
                                  RedirectAttributes redirectAttributes) {
        Planta planta = new Planta();
        planta.setCodigo(codigo);
        planta.setNombrecomun(nom_com);
        planta.setNombrecientifico(nom_cien);
        
        if (servPlanta.validarPlanta(planta)) {
            servPlanta.insertarPlanta(planta);
            redirectAttributes.addFlashAttribute("success", "Planta registrada con éxito.");
        } else {
            redirectAttributes.addFlashAttribute("error", "El código de la planta ya existe o es inválido.");
        }
        
        return "redirect:/gestionplantas"; 
    }

    /* Modificar Planta */
    @GetMapping("/modificarplanta")
    public String mostrarFormularioModificarPlanta(@RequestParam(required = false) String codPlanta, Model model) {
        if (codPlanta == null || codPlanta.isEmpty()) {
            model.addAttribute("error", "Código de planta no proporcionado.");
            List<Planta> listaPlantas = servPlanta.verPlantas();
            model.addAttribute("listaPlantas", listaPlantas);
            return "modificarplanta";  
        }

        Planta planta = servPlanta.findByCod(codPlanta);
        if (planta == null) {
            model.addAttribute("error", "Planta no encontrada con el código proporcionado.");
            List<Planta> listaPlantas = servPlanta.verPlantas();
            model.addAttribute("listaPlantas", listaPlantas);
            return "modificarplanta";  
        }

        model.addAttribute("planta", planta);
        List<Planta> listaPlantas = servPlanta.verPlantas();
        model.addAttribute("listaPlantas", listaPlantas);

        return "modificarplanta";  
    }

    @PostMapping("/modificarplanta")
    public String modificarPlanta(@RequestParam String codPlanta, 
                                  @RequestParam String nombreComun, 
                                  @RequestParam String nombreCientifico, 
                                  RedirectAttributes redirectAttributes) {

        if (nombreComun.trim().isEmpty() || nombreCientifico.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Todos los campos son obligatorios.");
            return "redirect:/modificarplanta?codPlanta=" + codPlanta;
        }

        Planta planta = servPlanta.findByCod(codPlanta);
        if (planta == null) {
            redirectAttributes.addFlashAttribute("error", "Planta no encontrada.");
            return "redirect:/gestionplantas";  
        }

        planta.setNombrecomun(nombreComun);
        planta.setNombrecientifico(nombreCientifico);
        
        try {
            servPlanta.modificar(planta);
            redirectAttributes.addFlashAttribute("success", "Planta modificada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al modificar la planta.");
        }

        return "redirect:/gestionplantas";  
    }


}
