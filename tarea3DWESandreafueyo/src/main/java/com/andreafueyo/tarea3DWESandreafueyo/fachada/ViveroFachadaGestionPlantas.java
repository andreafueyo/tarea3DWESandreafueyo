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
	ViveroFachadaAdmin admin;
	
	@Autowired
    @Lazy
	ViveroFachadaPrincipal portal;

	@Autowired
    @Lazy
	ViveroFachadaGestionPlantas gestPlantas;
	
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
                System.out.println("Opción incorrecta. Introduzca un número de los indicados.");
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
	
    /**
     * Registra una nueva planta en el sistema.
     * 
     * Solicita al usuario que introduzca el código, nombre común y nombre científico 
     * de la planta. Se aplican validaciones para garantizar que:
     * 1. El código no esté vacío.
     * 2. El código no contenga espacios.
     * 3. El código no esté ya registrado en la base de datos.
     * 
     * Una vez validados los datos, la planta se inserta en la base de datos.
     */
	
	public void registrarPlanta() {
		System.out.println("Introduzca los datos de la nueva planta.");
		System.out.println();
		System.out.println("Código: ");
		in.nextLine();
		String codigo = in.nextLine();
		Planta planta = new Planta();
		planta.setCodigo(codigo);
		
		while(codigo.isEmpty() || codigo.contains(" ") || !controlador.getServPlanta().validarPlanta(planta)) {
			if(codigo.contains(" ")) {
				System.out.println("Código de planta con espacio, introduzca otro código: ");
			}
			else if(!controlador.getServPlanta().validarPlanta(planta)) {
					System.out.println("Código de planta ya existente, vuelva a intentarlo: ");
			}
			
			codigo = in.nextLine();
			planta.setCodigo(codigo);

		}
		
		System.out.println("Nombre común: ");
		String nom_com = in.next();
		in.nextLine();
		System.out.println("Nombre científico: ");
		String nom_cien = in.next();
		
		Planta p = new Planta();
		p.setCodigo(codigo);
		p.setNombrecomun(nom_com);
		p.setNombrecientifico(nom_cien);
		
		controlador.getServPlanta().insertarPlanta(p);	
	}
	
    /**
     * Modifica los datos de una planta existente.
     * 
     * Primero muestra una lista de todas las plantas disponibles y solicita al usuario 
     * el código de la planta a modificar. Si el código no existe, se pide al usuario 
     * que lo reintroduzca.
     * 
     * Una vez validado el código, se solicita al usuario que introduzca los nuevos 
     * valores para el nombre común y el nombre científico de la planta, y los datos 
     * se actualizan en la base de datos.
     */
	
	public void modificarPlanta() {
		System.out.println("A continuación se muestran todas las plantas disponibles. Introduce el código de la planta a modificar.");
		System.out.println();
		portal.mostarPlantas();
		System.out.println();
		
		boolean plantaCorrecta = false;
		
		while(!plantaCorrecta) {
			System.out.println("Código de planta: ");
			String cod_planta = in.next();
			
			in.nextLine();
			System.out.println();
			
			Planta p = controlador.getServPlanta().findByCod(cod_planta);
			if(p == null) {
				System.out.println("No hay ninguna planta con ese código en nuestra base de datos, introduzca de nuevo otro número por favor.");
			}
			else {
				plantaCorrecta = true;
				System.out.println("Introduzca los nuevos datos.");
				System.out.println();
				System.out.println("Nombre común: ");
				String nom_com = in.nextLine();
				System.out.println("Nombre científico: ");
				String nom_cien = in.nextLine();
				p.setNombrecomun(nom_com);
				p.setNombrecientifico(nom_cien);
				controlador.getServPlanta().modificar(p);
			}			
		}
	}
		
}
