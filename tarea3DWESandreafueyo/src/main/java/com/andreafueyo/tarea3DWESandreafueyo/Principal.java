package com.andreafueyo.tarea3DWESandreafueyo;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;


//NUESTRA CAPA DE VISTA
public class Principal implements CommandLineRunner {

	@Autowired
	ServiciosEjemplar servejemplar;
	
	@Autowired
	ServiciosPlanta servplanta;
	
	/*antes era nuestro main, ahora pongo INSTRUCCIONES*/
	@Override
	public void run(String... args) throws Exception {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("INI");
		//Log.info("------------------------");
		
		Planta p1 = new Planta();
		p1.setNombrecomun("GIRASOL");
		p1.setNombrecientifico("Pipa pipae");
		
		if(!servplanta.validarPlanta(p1))
			System.out.println("El código ya existe.");
		
		Ejemplar ej1 = new Ejemplar();
		ej1.setPlanta(p1);
		p1.getEjemplares().add(ej1);
		
		
		//servplant.validarPlanta(p1);
		
		servplanta.insertarPlanta(p1);
		
		String nombreejemplar = p1.getNombrecomun().toUpperCase()+"_"+p1.getNombrecientifico();
		ej1.setNombre(nombreejemplar);
		servejemplar.actualizar(ej1);
		
		System.out.println("--------------");
		
		
		System.out.println("FIN");
		
	}

	
}
