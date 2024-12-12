package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.andreafueyo.tarea3DWESandreafueyo.control.Controlador;
import com.andreafueyo.tarea3DWESandreafueyo.control.ViveroServiciosConexion;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

@Controller
public class ViveroFachadaPersonal {
	
		Scanner in = new Scanner(System.in);
	
		private static ViveroFachadaPersonal personal;
		
		@Autowired
	    @Lazy
		ViveroFachadaAdmin admin;
		
		@Autowired
	    @Lazy
	    ViveroFachadaPrincipal portal;
		@Autowired
	    @Lazy
	    ViveroFachadaGestionEjemplares gestEjemp;
		@Autowired
	    @Lazy
	    ViveroFachadaGestionMensajes gestMens;
		
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
		
		public void mostrarMenuPersonal() {
			System.out.println();
			
	        int opcion = 0;
	        do {
	        	System.out.println("---MENÚ DE USUARIO---");
	    		System.out.println("Seleccione una opción:");
	    		System.out.println("1.  Ver plantas.");
	    		System.out.println("2.  Gestión de ejemplares.");
	    		System.out.println("3.  Gestión de mensajes.");
	    		System.out.println("4.  Cerrar sesión.");
	            
	    		try {
	    		opcion = in.nextInt();
	            if (opcion < 1 || opcion > 4) {
	                System.out.println("Opción incorrecta.");
	                continue;
	            }
	            switch (opcion) {
	            	case 1:
	            		portal.mostarPlantas();
	            		break;
	            	case 2:
	            		gestEjemp.mostrarMenuGestionEjemplares();
	            		break;
	            	case 3:
	            		gestMens.mostrarMenuGestionMensajes();
	            		break;
	            	case 4:
	            		portal.mostrarMenuPrincipal();
	            		break;
	            }
	    		  } catch (InputMismatchException e) {
	    				System.out.println("ERROR. Ingrese un número entero.");
	    				in.nextLine();
	    	        }
	        } while(opcion != 4);
		}
	
}
