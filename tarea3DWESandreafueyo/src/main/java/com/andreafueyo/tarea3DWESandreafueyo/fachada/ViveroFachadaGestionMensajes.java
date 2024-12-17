package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.andreafueyo.tarea3DWESandreafueyo.control.Controlador;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Ejemplar;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Mensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

@Controller
public class ViveroFachadaGestionMensajes {

	@Autowired
	@Lazy
	ViveroFachadaPrincipal portal;

	@Autowired
	Controlador controlador;

	@Autowired
	ServiciosCredenciales crServ;
	@Autowired
	ServiciosEjemplar ejServ;
	@Autowired
	ServiciosMensaje menServ;
	@Autowired
	ServiciosPersona perServ;
	@Autowired
	ServiciosPlanta plServ;
	
	Scanner in = new Scanner(System.in);
	
	
	public void mostrarMenuGestionMensajes() {
		System.out.println();
		
        int opcion = 0;
        do {
        	System.out.println("---MENÚ DE GESTIÓN DE MENSAJES---");
    		System.out.println("Seleccione una opción:");
    		System.out.println("1.  Registrar un nuevo mensaje.");
    		System.out.println("2.  Filtrar mensajes.");
    		System.out.println("3.  Volver al menú anterior.");
            
    	try {
    		opcion = in.nextInt();
            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción incorrecta.");
                continue;
            }
            switch (opcion) {
            	case 1:
            		this.registrarMensaje();
            		break;
            	case 2:
            		this.mostrarMenuFiltrarMensajes();
            		break;
            	case 3:
            		break;
            }
    	} catch (InputMismatchException e) {
			System.out.println("ERROR. Ingrese un número entero.");
			in.nextLine();
        }
        } while(opcion != 3);
	}
	
    /**
     * Registra un nuevo mensaje asociado a un ejemplar.
     * 
     * Solicita al usuario el ID del ejemplar existente y una anotación y se
     * registra en el sistema.
     */
	
	public void registrarMensaje(){

		System.out.println("A continuación se muestran todos los ejemplares. Introduce el código del ejemplar para el que realizar una anotación.");
		System.out.println();
		controlador.getServEjemplar().mostrarEjemplares();
		System.out.println();
		Ejemplar ej = null;
		Long id_ej = null;
		boolean ejemplarCorrecto = false;
		
		while(!ejemplarCorrecto) {
			try {
				System.out.println("Id de ejemplar: ");
				id_ej = (long) in.nextInt();
				in.nextLine();
				System.out.println();
				
				ej = controlador.getServEjemplar().findById(id_ej);
			}catch (InputMismatchException e) {
				in.nextLine();
	        }
			
			if(ej == null) {
				System.out.println("No hay ningún ejemplar con ese id en nuestra base de datos o no ha metido un número válido, introduzca de nuevo otro.");
			}
			else {
				ejemplarCorrecto = true;
			}			
		}
		System.out.println();
		System.out.println("Mensaje: ");
		String mensaje = in.nextLine();
		
		controlador.getServMensaje().registrarMensaje(id_ej, portal.getCredencial().getPersona().getId(), mensaje);
		System.out.println("¡Mensaje insertado!");
	}
	
    /**
     * Muestra un submenú para filtrar mensajes por criterios específicos.
     */
	
	public void mostrarMenuFiltrarMensajes(){
		System.out.println();
		
        int opcion = 0;
        do {
        	System.out.println("Elige por qué filtrar");
    		System.out.println("Seleccione una opción:");
    		System.out.println("1.  Persona que lo escribió.");
    		System.out.println("2.  Por rango de fechas.");
    		System.out.println("3.  Por tipo de planta.");
    		System.out.println("4.  Volver al menú anterior.");
            
    	try {
    		opcion = in.nextInt();
            if (opcion < 1 || opcion > 4) {
                System.out.println("Opción incorrecta.");
                continue;
            }
            switch (opcion) {
            	case 1:
            		this.filtrarPorPersona();
            		break;
            	case 2:
            		this.filtrarPorFechas();
            		break;
            	case 3:
            		this.filtrarPorPlanta();
            		break;
            	case 4:
            		break;
            }
    	} catch (InputMismatchException e) {
			System.out.println("ERROR. Ingrese un número entero.");
			in.nextLine();
        }
        } while(opcion != 4);
	}
	
    /**
     * Filtra mensajes por tipo de planta introducido por el usuario.
     */
	
	public void filtrarPorPlanta() {
		System.out.println("¿Por qué tipo de planta quieres buscar los mensajes?");
		System.out.println();
		System.out.println("Tipo: ");
		in.nextLine();
		String tipo = in.nextLine();
		List<Mensaje> listaMensajes = controlador.getServMensaje().findByTipoPlanta(tipo);
	 	if(listaMensajes == null || listaMensajes.isEmpty()) {
	 		System.out.println("No hay mensajes para este tipo de planta");
		 	System.out.println();
	 	}else {
			for(Mensaje m : listaMensajes) {
		 		System.out.println(m.toString());
		 	}
		 	System.out.println();
	 	}

	}
	
	   /**
     * Filtra mensajes por nombre de persona introducido por el usuario.
     */
	
	public void filtrarPorPersona() {
		System.out.println("¿Por qué persona quieres buscar los mensajes?");
		System.out.println();
		System.out.println("Persona: ");
		in.nextLine();
		String persona = in.nextLine();
		List<Mensaje> listaMensajes = controlador.getServMensaje().findByNombrePersona(persona);
	 	if(listaMensajes == null || listaMensajes.isEmpty()) {
	 		System.out.println("No hay mensajes para esta persona");
		 	System.out.println();
	 	}else {
			for(Mensaje m : listaMensajes) {
		 		System.out.println(m.toString());
		 	}
		 	System.out.println();
	 	}

	}
	
	   /**
     * Filtra mensajes por fecha introducido por el usuario.
     */
	
	public void filtrarPorFechas() {
		System.out.println("¿Entre qué fechas quieres buscar los mensajes?");
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;
        in.nextLine();
        while (fechaInicio == null) {
            System.out.print("Introduce la fecha de inicio (yyyy-MM-dd HH:mm): ");
            String input = in.nextLine();
            try {
                fechaInicio = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intenta de nuevo.");
            }
        }
        
        while (fechaFin == null) {
            System.out.print("Introduce la fecha final (yyyy-MM-dd HH:mm): ");
            String input = in.nextLine();
            try {
                fechaFin = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intenta de nuevo.");
            }
        }

		System.out.println();
		
		

		List<Mensaje> listaMensajes = controlador.getServMensaje().findMensajesEntreFechas(fechaInicio, fechaFin);
	 	if(listaMensajes == null || listaMensajes.isEmpty()) {
	 		System.out.println("No hay mensajes entre estas fechas");
		 	System.out.println();
	 	}else {
			for(Mensaje m : listaMensajes) {
		 		System.out.println(m.toString());
		 	}
		 	System.out.println();
	 	}

	}
	
}
