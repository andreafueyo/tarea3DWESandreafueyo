package com.andreafueyo.tarea3DWESandreafueyo.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Cliente;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Credenciales;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCliente;
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
	private ServiciosCliente servCliente;
	
	
	Controlador controlador;
	
	private String menuLogin;
	
	public MainController() {
		this.menuLogin = "menucliente";
	}
		
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
	
	/*Sesión CLIENTE*/
	@GetMapping("/menucliente")
	public String menuCliente(Model model) {
		return "menucliente"; 
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
	public String procesarRegistroPersona(@ModelAttribute Credenciales credenciales, Model model) {
		if(credenciales.getUsuario().contains(" ") || credenciales.getPassword().contains(" ")
				|| credenciales.getPersona().getEmail().contains(" ")) {
			model.addAttribute("error", "Usuario, contraseña o nombre no válidos. Introduzca de nuevo los datos sin espacios.");
			return "registrarpersona";
		}
		else {
			if (servCredenciales.validarNuevaCredencial(credenciales) 
					&& servPersona.findByEmail(credenciales.getPersona().getEmail()) == null) {
				credenciales.setRol("ROLE_PERSONAL");
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
	
	/*Registrar cliente*/
	@GetMapping("/registrarcliente")
	public String registrarCliente(Model model) {
		Credenciales credenciales = new Credenciales();
		credenciales.setCliente(new Cliente());
		model.addAttribute("credenciales", new Credenciales());
		return "registrarcliente";
	}

	@PostMapping("/registrarcliente")
	public String procesarRegistroCliente(@ModelAttribute Credenciales credenciales, Model model) {
	    String telefono = credenciales.getCliente().getTelefono();
	    String nif = credenciales.getCliente().getNIF();

	    if (credenciales.getUsuario().contains(" ") || credenciales.getPassword().contains(" ")
	            || credenciales.getCliente().getEmail().contains(" ")) {
	        model.addAttribute("error", "Usuario, contraseña o nombre no válidos. Introduzca de nuevo los datos sin espacios.");
	        return "registrarcliente";
	    }

	    if (telefono == null || !telefono.matches("^[0-9]{9}$")) {
	        model.addAttribute("error", "El número de teléfono debe tener exactamente 9 dígitos numéricos.");
	        return "registrarcliente";
	    }

	    LocalDate fechaNac = credenciales.getCliente().getFecha_nac();
	    if (fechaNac == null) {
	        model.addAttribute("error", "La fecha de nacimiento no puede estar vacía.");
	        return "registrarcliente";
	    }
	    
	    if (nif == null || !nif.matches("^[0-9]{8}[a-zA-Z]$")) {
	        model.addAttribute("error", "El DNI debe tener 8 números seguidos de una letra.");
	        return "registrarcliente";
	    }

	    if (servCredenciales.validarNuevaCredencial(credenciales) 
	            && servCliente.findByEmail(credenciales.getCliente().getEmail()) == null) {
	        credenciales.setRol("ROLE_CLIENTE");
	        servCredenciales.insertar(credenciales);
	        model.addAttribute("exito", "Usuario creado con éxito en nuestra base de datos.");
	        model.addAttribute("credenciales", new Credenciales());
	        return "registrarcliente";
	    } else {
	        model.addAttribute("error", "El usuario ya existe.");
	        return "registrarcliente";
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
