package com.andreafueyo.tarea3DWESandreafueyo.control;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaAdmin;
import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaPrincipal;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Credenciales;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;


@Controller
public class MainController {

    @Autowired
    private ServiciosPlanta servPlanta;
    @Autowired
    private ServiciosCredenciales servCredenciales;
    @Autowired
    private ViveroFachadaAdmin viveroFachadaAdmin;
    @Autowired
    private ViveroFachadaPrincipal portal;
	@Autowired
	Controlador controlador;

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
            credenciales = controlador.getServCredenciales().findByUsuario(credenciales.getUsuario());
            portal.setCredencial(credenciales);
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
    
    /*Gestión mensajes*/
    @GetMapping("/gestionmensajes")
    public String gestionMensajes(@RequestParam(value = "origen", required = false, defaultValue = "menuadmin") String origen, Model model) {
        model.addAttribute("origen", origen);  
        return "gestionmensajes"; 
    }

    /*Registrar persona*/
    @GetMapping("/registrarpersona")
    public String registrarPersona(Model model) {
        model.addAttribute("credenciales", new Credenciales());
        return "registrarpersona";
    }

    @PostMapping("/registrarpersona")
    public String procesarRegistro(@ModelAttribute Credenciales credenciales, Model model) {
        if (servCredenciales.validarNuevaCredencial(credenciales)) {
            servCredenciales.insertar(credenciales);
            return "redirect:/menuadmin"; 
        } else {
            model.addAttribute("error", "El usuario ya existe");
            return "registrarpersona";
        }
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
