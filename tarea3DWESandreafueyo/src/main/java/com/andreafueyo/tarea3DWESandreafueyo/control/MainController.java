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
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;


@Controller
public class MainController {

	@Autowired
	private ServiciosPlanta servPlanta;
	@Autowired
	private ServiciosCredenciales servCredenciales;
	@Autowired
	private ServiciosPersona servPersona;
	@Autowired
	private ViveroFachadaAdmin viveroFachadaAdmin;
	@Autowired
	private ViveroFachadaPrincipal portal;
	@Autowired
	Controlador controlador;
	
	private String menuLogin = "menupersonal";
		
    public String getMenuLogin() {
		return menuLogin;
	}

	public void setMenuLogin(String menuLogin) {
		this.menuLogin = menuLogin;
	}

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
				this.setMenuLogin("menuadmin");
				return "redirect:/menuadmin";
			} else {
				this.setMenuLogin("menupersonal");
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

	/*Registrar persona*/
	@GetMapping("/registrarpersona")
	public String registrarPersona(Model model) {
		Credenciales credenciales = new Credenciales();
		credenciales.setPersona(new Persona());
		model.addAttribute("credenciales", new Credenciales());
		return "registrarpersona";
	}

	@PostMapping("/registrarpersona")
	public String procesarRegistro(@ModelAttribute Credenciales credenciales, Model model) {
		if(credenciales.getUsuario().contains(" ") || credenciales.getPassword().contains(" ")
				|| credenciales.getPersona().getEmail().contains(" ")) {
			model.addAttribute("error", "Usuario, contraseña o nombre no válidos. Introduzca de nuevo los datos sin espacios.");
			return "registrarpersona";
		}
		else {
			if (servCredenciales.validarNuevaCredencial(credenciales) 
					&& servPersona.findByEmail(credenciales.getPersona().getEmail()) == null) {
				servCredenciales.insertar(credenciales);
				model.addAttribute("exito", "Usuario creado con éxito en nuestra base de datos.");
				model.addAttribute("credenciales", new Credenciales());
				return "registrarpersona";
			} else {
				model.addAttribute("error", "El usuario ya existe.");
				return "registrarpersona";
			}
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
