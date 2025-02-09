package com.andreafueyo.tarea3DWESandreafueyo.fachada;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.andreafueyo.tarea3DWESandreafueyo.control.Controlador;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Credenciales;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosEjemplar;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPersona;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosMensaje;
import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosPlanta;


@Controller
public class ViveroFachadaPrincipal {
	
	private Credenciales credencial;
	
	@Autowired
	ViveroFachadaAdmin admin;
	@Autowired
	ViveroFachadaPersonal personal;
	
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
	
	Scanner in = new Scanner(System.in);
	
	/**
	 * Clase controladora que gestiona el menú principal del vivero.
	 * 
	 * Esta clase actúa como la fachada principal para el usuario final. Permite 
	 * visualizar plantas disponibles, realizar un login para acceder a funcionalidades
	 * restringidas y delega acciones a otras fachadas según el rol del usuario.
	 * 
	 * Puedes acceder como administrador o como usuario.
	 * El nombre para acceder al menú de administrador es "admin" y la contraseña "admin".
	 * Uno de los perfiles de usuario que existe es "a" cuya contraseña es "a".
	 * 
	 * @author Andrea
	 */
	
	
	public void mostrarMenuPrincipal() {
		
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("¡Bienvenido/a a nuestro vivero! ¿Qué desea hacer?");
		
        int opcion = 0;
        do {
    		System.out.println("Seleccione una opción:");
    		System.out.println("1.  Ver plantas.");
    		System.out.println("2.  Login.");
    		
    		try {
	            opcion = in.nextInt();
	            if (opcion < 1 || opcion > 2) {
	                System.out.println("Opción incorrecta. Inserte uno de los números indicados.");
	                continue;
            }
            switch (opcion) {
            	case 1:
            		this.mostarPlantasDetalle();
            		break;
            	case 2:
            		this.mostrarMenuLogin();
            		break;
            }
    		} catch (InputMismatchException e) {
				System.out.println("ERROR. Ingrese un número entero.");
				in.nextLine();
	        }
        } while(opcion != 2);
	}
		
	
	public void mostrarMenuLogin() {
		
		Credenciales c = this.pedirCredenciales();
		
		boolean loginCorrecto = false;
		do {
			if(c.getUsuario().equals("admin") && c.getPassword().equals("admin")) {
				System.out.println("¡Hola, admin!, ¿qué desea hacer?");
				credencial = controlador.getServCredenciales().findByUsuario(c.getUsuario());
				loginCorrecto = true;
				admin.mostrarMenuAdmin();	
			}
			else {
				if(!controlador.getServCredenciales().validarCredencialContraseña(c)) {
					System.out.println("Usuario o contraseña incorrectos, vuelva a introducir los datos.");
					System.out.println();
					c = this.pedirCredenciales();
				} else {
					loginCorrecto = true;
					credencial = controlador.getServCredenciales().findByUsuario(c.getUsuario());
					//USUARIO: andre CONTRASEÑA: andre || USUARIO: a CONTRASEÑA: a
					System.out.println("¡Hola, "+c.getUsuario()+"! ¿Qué desea hacer?");
					personal.mostrarMenuPersonal();		
				}
			}
		} while (!loginCorrecto);
		
		
	}
		
    /**
     * Solicita las credenciales de acceso al usuario.
     * 
     * @return Un objeto {@link Credenciales} con los datos ingresados por el usuario.
     */
	
	public Credenciales pedirCredenciales() {
		
		System.out.println("Introduzca las credenciales de acceso.");
		System.out.println();
		System.out.println("Usuario: ");
		String usuario = in.next();
		System.out.println("Contraseña: ");
		String contraseña = in.next();
		
		Credenciales c = new Credenciales();
		c.setUsuario(usuario);
		c.setPassword(contraseña);
		
		return c;
	}
	
	
	//MÉTODOS COMUNES A TODAS LAS FACHADAS
	
    /**
     * Muestra la lista de plantas disponibles en el vivero.
     * 
     * Consulta a través del controlador las plantas disponibles y las muestra
     * al usuario en orden numerado.
     * No hace falta inidicar sesión, es lo que consideramos el perfil de invitado.
     */
	
	public void mostarPlantas() {
		System.out.println();
		System.out.println("Estas son las plantas: ");
	 	List<Planta> listaPlantas = controlador.getServPlanta().verPlantas();
		
		int contador = 1;
	 	for(Planta p : listaPlantas) {
	 		System.out.println(contador + ": " + p.toString());
	 		contador++;
	 	}
	 	System.out.println();
	}
	
	public Credenciales getCredencial() {
		return credencial;
	}
	
	public void setCredencial(Credenciales credencial) {
		this.credencial = credencial;
	}
	
	public void mostarPlantasDetalle() {
		System.out.println();
		System.out.println("Estas son las plantas: ");
	 	List<Planta> listaPlantas = controlador.getServPlanta().verPlantas();
		
		int contador = 1;
	 	for(Planta p : listaPlantas) {
	 		System.out.println(contador + ": " + p.toString());
	 		contador++;
	 	}
	 	System.out.println();
	 	
	 	System.out.println("Selecciona una de esas plantas para mostrar más detalle.");
		System.out.println();
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
		
		System.out.println(controlador.getServPlanta().verPlantaDetalle(p));
		System.out.println();
	}
	
}
