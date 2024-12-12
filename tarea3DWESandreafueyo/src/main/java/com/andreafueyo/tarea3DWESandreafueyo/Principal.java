package com.andreafueyo.tarea3DWESandreafueyo;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaGestionEjemplares;
import com.andreafueyo.tarea3DWESandreafueyo.fachada.ViveroFachadaPrincipal;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.EjemplarRepository;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;


//NUESTRA CAPA DE VISTA
@Component
public class Principal implements CommandLineRunner {

	@Autowired
	ServiciosEjemplar servejemplar;
	
	@Autowired
	ServiciosPlanta servplanta;
		
	@Autowired
	ViveroFachadaPrincipal portal;
	
	/*antes era nuestro main, ahora pongo INSTRUCCIONES*/
	@Override
	public void run(String... args) throws Exception {
				
			
		System.out.println("INICIO");
	
		System.out.println("Programa de gestión de un invernadero");
				
		portal.mostrarMenuPrincipal();
				
		
	}

	
}
