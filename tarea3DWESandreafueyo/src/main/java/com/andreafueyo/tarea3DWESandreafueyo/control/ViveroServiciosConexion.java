package com.andreafueyo.tarea3DWESandreafueyo.control;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;

public class ViveroServiciosConexion {

	public static ViveroServiciosConexion servicios;

	public static ViveroServiciosConexion getServicios() {
		if (servicios == null)
				servicios = new ViveroServiciosConexion();
		return servicios;
	}
	
	public ServiciosCredenciales getServiciosCredenciales() {
		return new ServiciosCredenciales();
	}

	public ServiciosEjemplar getServiciosEjemplar() {
		return new ServiciosEjemplar();
	}
	
	public ServiciosMensaje getServiciosMensaje() {
		return new ServiciosMensaje();
	}
	
	public ServiciosPersona getServiciosPersona() {
		return new ServiciosPersona();
	}
	
	public ServiciosPlanta getServiciosPlanta() {
		return new ServiciosPlanta();
	}
	
}
