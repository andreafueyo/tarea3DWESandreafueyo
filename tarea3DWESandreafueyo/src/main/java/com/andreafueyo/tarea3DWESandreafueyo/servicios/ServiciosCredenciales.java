package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.repositorios.CredencialesRepository;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PersonaRepository;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Credenciales;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Persona;

@Service
public class ServiciosCredenciales {
	
	@Autowired
	CredencialesRepository credencialesrepo;
	@Autowired
	PersonaRepository personarepo;

	public boolean validarNuevaCredencial(Credenciales c) { //Si ya existe, devuelve false
		
		if(this.findByUsuario(c.getUsuario()) == null) {
			return true;
		}
		else {
			return false;
		}
		
	}	
	
	public boolean validarCredencialContraseña(Credenciales c) { //Si coincide, devuelve true
		if(this.findByUsuario(c.getUsuario()) == null) {
			return false;
		}
		else if(this.findByUsuario(c.getUsuario()).getPassword().equals(c.getPassword())){
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public String validarTipoUsuario(Credenciales c) { //Devuelve el tipo de usuario
		if(c.getUsuario().equals("invitado") && c.getPassword().equals("invitado")) {
			return "invitado";
		}
		else if(c.getUsuario().equals("admin") && c.getPassword().equals("admin")) {
			return "admin";
		} else {
				return "personal";
		}
	}
	
	public Credenciales insertar(Credenciales c) {
		return credencialesrepo.saveAndFlush(c);
	}

	public Credenciales findByUsuario(String usuario) {
		return credencialesrepo.findByUsuario(usuario);
	}

	public List<Credenciales> findAll(){
		return credencialesrepo.findAll();
	}

	public Credenciales registrarCredencial(String usuario, String password, String email) {
				
		Long id_persona = personarepo.findByEmail(email).getId();
		Persona persona = personarepo.findByPersonaId(id_persona);
		Credenciales c = new Credenciales();
		c.setUsuario(usuario);
		c.setPassword(password);
		c.setPersona(persona);
		
		return this.insertar(c);
	}	
}
