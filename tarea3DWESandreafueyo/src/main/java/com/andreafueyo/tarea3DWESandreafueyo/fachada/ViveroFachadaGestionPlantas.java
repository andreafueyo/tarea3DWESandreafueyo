package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.andreafueyo.tarea3DWESandreafueyo.control.Controlador;
import com.andreafueyo.tarea3DWESandreafueyo.control.ViveroServiciosConexion;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;

@Controller
public class ViveroFachadaGestionPlantas {
	
	Scanner in = new Scanner(System.in);
	
	@Autowired
    @Lazy
	private static ViveroFachadaAdmin admin;
	
	@Autowired
    @Lazy
	private static ViveroFachadaPrincipal portal;

	@Autowired
    @Lazy
	private static ViveroFachadaGestionPlantas gestPlantas;
	
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
	

	public static ViveroFachadaGestionPlantas getPortal() {
		if (gestPlantas==null)
			gestPlantas = new ViveroFachadaGestionPlantas();
		return gestPlantas;
	}
	
	public void mostrarMenuGestionPlantas() {
		System.out.println();
		
        int opcion = 0;
        do {
        	System.out.println("---MENÚ DE PLANTAS---");
    		System.out.println("Seleccione una opcion:");
    		System.out.println("1.  Registrar una nueva planta.");
    		System.out.println("2.  Modificar una planta existente.");
    		System.out.println("3.  Volver al menú del administrador.");
            
    	try {
    		opcion = in.nextInt();
            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción incorrecta.");
                continue;
            }
            switch (opcion) {
            	case 1:
            		this.registrarPlanta();
            		break;
            	case 2:
            		this.modificarPlanta();
            		break;
            	case 3:
            		break;
            	case 4:
            }
    	} catch (InputMismatchException e) {
			System.out.println("ERROR. Ingrese un número entero.");
			in.nextLine();
        }
        } while(opcion != 3);
	}
	
	public void registrarPlanta() {
		System.out.println("Introduzca los datos de la nueva planta.");
		System.out.println();
		System.out.println("Código: ");
		String codigo = in.next();
		Planta planta = new Planta();
		planta.setCodigo(codigo);
		
		while(controlador.getServPlanta().validarPlanta(planta)) {
			System.out.println("Código de planta ya existente, vuelva a intentarlo: ");
			in.nextLine();
			codigo = in.nextLine();
		}
		System.out.println("Nombre común: ");
		String nom_com = in.next();
		System.out.println("Nombre científico: ");
		String nom_cien = in.next();
		
		Planta p = new Planta();
		p.setCodigo(codigo);
		p.setNombrecomun(nom_com);
		p.setNombrecientifico(nom_cien);
		
		controlador.getServPlanta().insertarPlanta(p);	
	}
	
	public void modificarPlanta() {
		System.out.println("A continuación se muestran todas las plantas disponibles. Introduce el código de la planta a modificar.");
		System.out.println();
		portal.mostarPlantas();
		System.out.println();
		System.out.println("Código de planta: ");
		String cod_planta = in.next();
		
		in.nextLine();
		System.out.println();
		System.out.println("Introduzca los nuevos datos.");
		System.out.println();
		System.out.println("Nombre común: ");
		String nom_com = in.next();
		System.out.println("Nombre científico: ");
		String nom_cien = in.next();
		
		Planta p = new Planta();
		p.setCodigo(cod_planta);
		p.setNombrecomun(nom_com);
		p.setNombrecientifico(nom_cien);
		
		controlador.getServPlanta().modificar(p);	
	}
		
}
