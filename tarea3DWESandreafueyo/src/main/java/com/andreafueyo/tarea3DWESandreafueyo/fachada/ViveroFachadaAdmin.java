package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.andreafueyo.tarea3DWESandreafueyo.control.Controlador;
import com.andreafueyo.tarea3DWESandreafueyo.control.ViveroServiciosConexion;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;

@Controller
public class ViveroFachadaAdmin {

	Scanner in = new Scanner(System.in);
	
	@Autowired
    @Lazy
    ViveroFachadaPrincipal portal;
	@Autowired
	ViveroFachadaPersonal personal;
	@Autowired
    @Lazy
	ViveroFachadaGestionEjemplares gestEjemp;
	@Autowired
    @Lazy
	ViveroFachadaGestionMensajes gestMens;
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
	
	
	public void mostrarMenuAdmin() {
		
        int opcion = 0;
        do {
        	System.out.println("---MENÚ ADMINISTRADOR GENERAL---");
    		System.out.println("Seleccione una opcion:");
    		System.out.println("1.  Ver plantas.");
    		System.out.println("2.  Registrar persona.");
    		System.out.println("3.  Gestión de plantas.");
    		System.out.println("4.  Gestión de ejemplares.");
    		System.out.println("5.  Gestión de mensajes.");
    		System.out.println("6.  Cerrar sesión.");

            try {
            	opcion = in.nextInt();
            	
            	  if (opcion < 1 || opcion > 6) {
                      System.out.println("Opción incorrecta. Introduzca un número de los indicados.");
                      continue;
            }
            
            switch (opcion) {
            	case 1:
            		portal.mostarPlantas();
            		break;
            	case 2:
            		this.registrarPersona();
            		break;
            	case 3:
            		gestPlantas.mostrarMenuGestionPlantas();
            		break;
            	case 4:
            		gestEjemp.mostrarMenuGestionEjemplares();
            		break;
            	case 5:
            		gestMens.mostrarMenuGestionMensajes();
            		break;
            	case 6:
            		portal.mostrarMenuPrincipal();
            		break;
            }
         
        } catch (InputMismatchException e) {
			System.out.println("ERROR. Ingrese un número entero.");
			in.nextLine();
        }
        } while(opcion != 6);
	}
        
	
	public void registrarPersona() {
		
		in.nextLine();
		
		System.out.println("--REGISTRO DE NUEVO USUARIO--");
		System.out.println("Introduzca los datos del nuevo usuario.");
		System.out.println();
		
		String nombre;
		String email;
		
		do {
			System.out.println("Nombre: ");
			nombre = in.nextLine();
			System.out.println("Email: ");
			email = in.nextLine();
			if(nombre.contains(" ") || email.contains(" ")) {
				System.out.println("Nombre o email no válidos. Introduzca de nuevo los datos sin espacios.");
			}
			if(controlador.getServPersona().findByEmail(email.toLowerCase()) != null) {
				System.out.println("Email ya registrado. Introduzca de nuevo otro email.");
			}
		} while (nombre.contains(" ") || email.contains(" ") || 
				controlador.getServPersona().findByEmail(email) != null);
		String usuario;
		String contrasena;
		do {
			System.out.println("Usuario: ");
			usuario = in.nextLine();
			System.out.println("Contraseña: ");
			contrasena = in.nextLine();
			if(usuario.contains(" ") || contrasena.contains(" ")) {
				System.out.println("Usuario o contraseña no válidos. Introduzca de nuevo los datos sin espacios.");
			}
			if(controlador.getServCredenciales().findByUsuario(usuario.toLowerCase()) != null) {
				System.out.println("Usuario ya registrado. Introduzca de nuevo otro email.");
			}
		} while (usuario.contains(" ") || contrasena.contains(" ") ||
				controlador.getServCredenciales().findByUsuario(usuario) != null);
		controlador.getServPersona().registrarPersona(nombre, email);
		controlador.getServCredenciales().registrarCredencial(usuario,contrasena, email);
		System.out.println("¡Usuario creado con éxito!");
	}
}
