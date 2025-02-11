package com.andreafueyo.tarea3DWESandreafueyo.control;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaAdmin;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Credenciales;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

import jakarta.servlet.http.HttpFilter;

@Controller
public class MainController {

    @Autowired
    private ServiciosPlanta servPlanta;
    @Autowired
    private ServiciosCredenciales servCredenciales;
    @Autowired
    private ViveroFachadaAdmin viveroFachadaAdmin;

    /*Ver plantas*/
    @GetMapping("/verplantas")
    public String verPlantas(Model model) {
        List<Planta> listaPlantas = servPlanta.verPlantas();
        model.addAttribute("plantas", listaPlantas);
        return "verplantas";  
    }
    
    /*Iniciar sesión*/
    @GetMapping("/iniciarsesion")
    public String inicioSesion() {
        return "iniciarsesion";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("usuario") String usuario,
                        @RequestParam("contrasena") String contrasena,
                        Model model) {
        
        Credenciales credenciales = new Credenciales();
        credenciales.setUsuario(usuario);
        credenciales.setPassword(contrasena);

        if (servCredenciales.validarCredencialContraseña(credenciales)) {
            model.addAttribute("usuario", usuario);
            
            String tipoUsuario = servCredenciales.validarTipoUsuario(credenciales);
            
            if ("admin".equals(tipoUsuario)) {
                return "redirect:/menuadmin";
            } else {
                return "redirect:/menupersonal";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
            return "iniciarsesion";
        }
    }

    /*Sesión ADMIN*/
    @GetMapping("/menuadmin")
    public String menuAdmin(Model model) {
        return "menuadmin"; 
    }
    
    /*Sesión USUARIO*/
    @GetMapping("/menupersonal")
    public String menuPersonal(Model model) {
        return "menupersonal"; 
    }
    
    /*Gestión plantas*/
    @GetMapping("/gestionplantas")
    public String gestionPlantas(Model model) {
        return "gestionplantas"; 
    }
    
    /*Gestión ejemplares*/
    @GetMapping("/gestionejemplares")
    public String gestionEjemplares(@RequestParam(value = "origen", required = false, defaultValue = "menuadmin") String origen, Model model) {
        model.addAttribute("origen", origen);  
        return "gestionejemplares"; 
    }
    
    /*Gestión mensajes*/
    @GetMapping("/gestionmensajes")
    public String gestionMensajes(@RequestParam(value = "origen", required = false, defaultValue = "menuadmin") String origen, Model model) {
        model.addAttribute("origen", origen);  
        return "gestionmensajes"; 
    }

    @GetMapping("/registrarpersona")
    public String registrarPersona(Model model) {
        model.addAttribute("persona", new Persona());
        return "registrarpersona"; 
    }

    /*Cerrar sesión*/
    @GetMapping("/cerrarsesion")
    public String cerrarSesion(SessionStatus status) {
        status.setComplete(); 
        return "redirect:/"; 
    }
 
    /*Raíz*/
    @GetMapping("/")
    public String index(Model model) {
    	return "index";
    }
    
}
