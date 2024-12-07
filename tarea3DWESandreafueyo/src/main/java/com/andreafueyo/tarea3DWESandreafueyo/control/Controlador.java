package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;

public class Controlador {
	
private static Controlador servicios;
	
	private ServiciosPlanta servPlanta;
	private ServiciosCredenciales servCredenciales;
	private ServiciosEjemplar servEjemplar;
	private ServiciosMensaje servMensaje;
	private ServiciosPersona servPersona;
	
	public static Controlador getServicios() {
		if(servicios == null) 
			servicios = new Controlador();
		return servicios;
	}
	
	private Controlador() {
		servPlanta = new ServiciosPlanta();
		servCredenciales = new ServiciosCredenciales();
		servEjemplar = new ServiciosEjemplar();
		servMensaje = new ServiciosMensaje();
		servPersona = new ServiciosPersona();		
	}

	
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


}
