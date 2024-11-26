package com.andreafueyo.tarea3DWESandreafueyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;


//NUESTRA CAPA DE VISTA
public class Principal implements CommandLineRunner {

	@Autowired
	ServiciosEjemplar servejemplar;
	
	@Autowired
	ServiciosPlanta servplant;
	
	/*antes era nuestro main, ahora pongo INSTRUCCIONES*/
	@Override
	public void run(String... args) throws Exception {
		System.out.println("INI");
		
		Planta p = new Planta();
		servplant.validarPlanta(p);
		
		servplant.insertarPlanta(p);
		
		System.out.println("--------------");
		
		
		System.out.println("FIN");
		
	}

	
}
