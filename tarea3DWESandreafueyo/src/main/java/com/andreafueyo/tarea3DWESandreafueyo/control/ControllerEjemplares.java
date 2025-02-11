package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerEjemplares {

    @Autowired
    private ServiciosEjemplar serviciosEjemplar;


    @GetMapping("/gestionejemplares")
    public String gestionEjemplares(@RequestParam(value = "origen", required = false, defaultValue = "menuadmin") String origen, Model model) {
        model.addAttribute("origen", origen);  
        return "gestionejemplares"; 
    }




}
