package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.util.InputMismatchException;
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
	private static ViveroFachadaPrincipal portal;
	@Autowired
    @Lazy
	private static ViveroFachadaGestionEjemplares gestEjemp;
	
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
	
	
	public static ViveroFachadaGestionEjemplares getPortal() {
		if (gestEjemp==null)
			gestEjemp = new ViveroFachadaGestionEjemplares();
		return gestEjemp;
	}
	
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
	
	
	
	public void registrarEjemplar() {
		
		in.nextLine();
		System.out.println("A continuación se muestran todas las plantas disponibles. Introduce el código de la planta sobre la que quieres crear un ejemplar nuevo.");
		System.out.println();
		portal.mostarPlantas();
		System.out.println("Código de planta: ");
		String cod_planta = in.nextLine();
		Planta pValidar = new Planta();
		pValidar.setCodigo(cod_planta);
		
		while(!controlador.getServPlanta().validarPlanta(pValidar)) {
			System.out.println("Código de planta incorrecto, vuelva a intentarlo: ");
			cod_planta = in.nextLine();
		}
		Planta p = controlador.getServPlanta().findByCod(cod_planta);
		controlador.getServEjemplar().registrarEjemplar(p, portal.getCredencial().getPersona().getId());
				
	}
	
	
	public void filtrarEjemplares() {
		
	}
	
	
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
