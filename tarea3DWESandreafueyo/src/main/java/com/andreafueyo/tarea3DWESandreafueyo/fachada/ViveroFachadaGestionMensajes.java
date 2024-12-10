package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.andreafueyo.tarea3DWESandreafueyo.control.Controlador;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

@Controller
public class ViveroFachadaGestionMensajes {

//private static ViveroFachadaGestionMensajes gestMens;

	@Autowired
	@Lazy
	ViveroFachadaPrincipal portal;

	@Autowired
	Controlador controlador;

	Scanner in = new Scanner(System.in);

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
	
	
//	public static ViveroFachadaGestionMensajes getPortal() {
//		if (gestMens==null)
//			gestMens = new ViveroFachadaGestionMensajes();
//		return gestMens;
//	}
	
	public void mostrarMenuGestionMensajes() {
		System.out.println();
		
        int opcion = 0;
        do {
        	System.out.println("---MENÚ DE GESTIÓN DE MENSAJES---");
    		System.out.println("Seleccione una opción:");
    		System.out.println("1.  Registrar un nuevo mensaje.");
    		System.out.println("2.  Filtrar mensajes (vacío).");
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
	
	public void registrarMensaje(){

		System.out.println("A continuación se muestran todos los ejemplares. Introduce el código del ejemplar para el que realizar una anotación.");
		System.out.println();
		controlador.getServEjemplar().mostrarEjemplares();
		System.out.println();
		System.out.println("Código de ejemplar: ");
		Long id_ej = (long) in.nextInt();
		in.nextLine();
		System.out.println();
		System.out.println("Mensaje: ");
		String mensaje = in.nextLine();

		controlador.getServMensaje().registrarMensaje(id_ej, portal.getCredencial().getPersona().getId(), mensaje);
		
	}
	
	public void mostrarMenuFiltrarMensajes(){
		
	}
	
}
