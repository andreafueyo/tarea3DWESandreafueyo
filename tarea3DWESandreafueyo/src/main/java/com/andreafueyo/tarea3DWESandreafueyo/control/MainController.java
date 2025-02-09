package com.andreafueyo.tarea3DWESandreafueyo.control;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

@Controller
public class MainController {

    @Autowired
    private ServiciosPlanta servPlanta;

    @GetMapping("/verplantas")
    public String verPlantas(Model model) {
        List<Planta> listaPlantas = servPlanta.verPlantas();
        model.addAttribute("plantas", listaPlantas);
        return "verplantas";  
    }
    
    @GetMapping("/iniciarsesion")
    public String inicioSesion() {
        return "iniciarsesion";
    }
    
    @GetMapping("/menuadmin")
    public String menuAdmin(Model model) {
        return "menuadmin"; 
    }
    
    @GetMapping("/")
    public String index(Model model) {
    	return "index";
    }
    
}
