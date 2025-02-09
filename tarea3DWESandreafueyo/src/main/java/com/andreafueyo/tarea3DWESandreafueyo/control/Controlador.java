package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEnfermedad;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosParasito;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;

@Service
public class Controlador {
	
	@Autowired
	private ServiciosPlanta servPlanta;
	@Autowired
	private ServiciosCredenciales servCredenciales;
	@Autowired
	private ServiciosEjemplar servEjemplar;
	@Autowired
	private ServiciosMensaje servMensaje;
	@Autowired
	private ServiciosPersona servPersona;
	@Autowired
	private ServiciosEnfermedad servEnfermedad;
	@Autowired
	private ServiciosParasito servParasito;
		
	public ServiciosPlanta getServPlanta() {
		return servPlanta;
	}

	public ServiciosCredenciales getServCredenciales() {
		return servCredenciales;
	}

	public ServiciosEjemplar getServEjemplar() {
		return servEjemplar;
	}

	public ServiciosMensaje getServMensaje() {
		return servMensaje;
	}

	public ServiciosPersona getServPersona() {
		return servPersona;
	}
	
	public ServiciosEnfermedad getServEnfermedad() {
		return servEnfermedad;
	}
	
	public ServiciosParasito getServParasito() {
		return servParasito;
	}


}
