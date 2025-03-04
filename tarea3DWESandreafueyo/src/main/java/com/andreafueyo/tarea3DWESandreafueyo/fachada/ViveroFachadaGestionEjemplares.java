package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.andreafueyo.tarea3DWESandreafueyo.control.Controlador;
import com.andreafueyo.tarea3DWESandreafueyo.control.ViveroServiciosConexion;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;

@Controller
public class ViveroFachadaGestionEjemplares {
	
	Scanner in = new Scanner(System.in);
	
	@Autowired
    @Lazy
	ViveroFachadaPrincipal portal;
	@Autowired
    @Lazy
	ViveroFachadaGestionEjemplares gestEjemp;
	
	@Autowired
    @Lazy
	ViveroServiciosConexion conServicios;

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
	
	@Autowired
	Controlador controlador;
	
	
	public void mostrarMenuGestionEjemplares() {
		
        int opcion = 0;
        do {
        	System.out.println("---MENÚ DE GESTIÓN DE EJEMPLARES---");
    		System.out.println("Seleccione una opción:");
    		System.out.println("1.  Registrar un nuevo ejemplar.");
    		System.out.println("2.  Filtrar ejemplares por tipo de planta (vacío).");
    		System.out.println("3.  Ver mensajes de seguimiento de un ejemplar.");
    		System.out.println("4.  Volver al menú anterior.");
    		
    		try {
    			opcion = in.nextInt();
    			if (opcion < 1 || opcion > 4) {
    				System.out.println("Opción incorrecta.");
    				continue;
    			}
            
            switch (opcion) {
            	case 1:
            		this.registrarEjemplar();
            		break;
            	case 2:
            		this.filtrarEjemplares();
            		break;
            	case 3:
            		this.verMensajes();
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
     * Registra un nuevo ejemplar asociado a una planta existente.
     * 
     * Este método pide al usuario que introduzca el código de una planta existente y una anotación.
     * Una vez validados los datos, se crea un nuevo ejemplar en el sistema relacionado a la planta.
     *
     */
	
	public void registrarEjemplar() {
		
		in.nextLine();
		System.out.println("A continuación se muestran todas las plantas disponibles. Introduce el código de la planta sobre la que quieres crear un ejemplar nuevo.");
		System.out.println();
		portal.mostarPlantas();
		Planta p = null;
		boolean plantaCorrecta = false;
		
		while(!plantaCorrecta) {
			System.out.println("Código de planta: ");
			String cod_planta = in.next();
			
			in.nextLine();
			System.out.println();
			
			p = controlador.getServPlanta().findByCod(cod_planta);
			if(p == null) {
				System.out.println("No hay ninguna planta con ese código en nuestra base de datos, introduzca de nuevo otro número por favor.");
			}
			else {
				plantaCorrecta = true;
			}			
		}
		//System.out.println("Introduzca una anotación: ");
		//String mensaje = in.nextLine();
		
		String mensaje = "Ejemplar insertado por la persona: " + portal.getCredencial().getPersona().getNombre();
		controlador.getServEjemplar().registrarEjemplar(p, portal.getCredencial().getPersona().getId(), mensaje);
		System.out.println("¡Ejemplar insertado!");
	}
	
    /**
     * Filtra los ejemplares por tipos de planta introducidos por el usuario.
     * 
     * Este método permite al usuario introducir una lista de tipos de planta para filtrar 
     * los ejemplares asociados a esos tipos. El proceso se finaliza cuando el usuario 
     * escribe 'FIN'.
     * NO FUNCIONA, solo muestra un mensaje.
     */
	
	public void filtrarEjemplares() {
        List<String> tipos = new ArrayList<>();
        
        System.out.println("Introduce los tipos de planta uno por uno. Escribe 'FIN' para terminar:");
        
        in.nextLine();
        while (true) {
            System.out.print("Tipo de planta: ");
            String tipo = in.nextLine().trim();
            
            if (tipo.equalsIgnoreCase("FIN")) {
                break;
            }
            
            if(tipo.isEmpty()) {
            	System.out.println("El tipo de planta está vacío. Inténtalo de nuevo.");
            }
            else {
            	Planta p = new Planta();
            	p.setCodigo(tipo);
            	
            	//si es true, es que no existe y no es valido
            	if(plServ.validarPlanta(p)) {
                	System.out.println("No existe una planta con ese código. Inténtalo de nuevo.");
            	}
            	else {
                    tipos.add(tipo);
            	}
            }
            
        }

		controlador.getServEjemplar().buscarEjemplaresPorTipos(tipos);
	
	}
	
	/**
     * Muestra los mensajes de seguimiento asociados a un ejemplar específico.
     * 
     * Este método solicita al usuario que introduzca el código de un ejemplar existente
     * y muestra los mensajes de seguimiento asociados a dicho ejemplar.
     *
     */
	
	public void verMensajes() {
	
		System.out.println("A continuación se muestran todos los ejemplares. Introduce el código del ejemplar para el que mostrar sus mensajes.");
		System.out.println();
		controlador.getServEjemplar().mostrarEjemplares();
		System.out.println();
		System.out.println("Código de ejemplar: ");
		Long id_ej = (long) in.nextInt();

		controlador.getServEjemplar().verMensajes(id_ej);
				
	}
	
}
