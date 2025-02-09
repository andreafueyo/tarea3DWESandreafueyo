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
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Enfermedad;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Parasito;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
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
    		System.out.println("3.  Registrar enfermedad.");
    		System.out.println("4.  Registrar parásito.");
    		System.out.println("5.  Modificar parásito.");
    		System.out.println("6.  Gestión de plantas.");
    		System.out.println("7.  Gestión de ejemplares.");
    		System.out.println("8.  Gestión de mensajes.");
    		System.out.println("9.  Cerrar sesión.");

            try {
            	opcion = in.nextInt();
            	
            	  if (opcion < 1 || opcion > 9) {
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
            		this.registrarEnfermedad();
            		break;
            	case 4:
            		this.registrarParasito();
            		break;
            	case 5:
            		this.modificarParasito();
            		break;
            	case 6:
            		gestPlantas.mostrarMenuGestionPlantas();
            		break;
            	case 7:
            		gestEjemp.mostrarMenuGestionEjemplares();
            		break;
            	case 8:
            		gestMens.mostrarMenuGestionMensajes();
            		break;
            	case 9:
            		portal.mostrarMenuPrincipal();
            		break;
            }
         
        } catch (InputMismatchException e) {
			System.out.println("ERROR. Ingrese un número entero.");
			in.nextLine();
        }
        } while(opcion != 9);
	}
        

	private void registrarParasito() {
		System.out.println("Introduzca los datos del nuevo parásito.");
		System.out.println();
		System.out.println("Nombre: ");
		in.nextLine();
		String nombre = in.nextLine();
		Parasito parasito = new Parasito();
		parasito.setNombre(nombre);
		
		while(controlador.getServParasito().findByNombre(nombre) != null) {
			System.out.println("Nombre de parásito ya existente, vuelva a intentarlo: ");
			nombre = in.nextLine();
			parasito.setNombre(nombre);
		}
		
		System.out.println("Color: ");
		String color = in.next();
		in.nextLine();
		parasito.setColor(color);
		
		controlador.getServParasito().insertarParasito(parasito);	
		System.out.println("¡Parásito insertado!");
		System.out.println();
		
	}
	
	 private void modificarParasito() {
		 System.out.println("A continuación se muestran todos los parásitos registrador en nuestra base de datos.");
		 System.out.println();
		 List<Parasito> listaParasitos = controlador.getServParasito().mostrarParasitos();
			int contador = 1;
		 	for(Parasito para : listaParasitos) {
		 		System.out.println(contador + ": " + para.toString());
		 		contador++;
		 	}
		 System.out.println();
		 System.out.println("Introduzca el nombre del parásito a modificar.");
		 System.out.println();
		 System.out.println("Nombre: ");
		 in.nextLine();
		 String nombre = in.nextLine();
		 Parasito parasito = new Parasito();
		 parasito.setNombre(nombre);
		 
		 while(controlador.getServParasito().findByNombre(nombre) == null) {
			 System.out.println("El nombre del parásito no existe, vuelva a intentarlo: ");
			 nombre = in.nextLine();
			 parasito.setNombre(nombre);
			}
			
			parasito = controlador.getServParasito().findByNombre(nombre);
			
			System.out.println("Color: ");
			String color = in.next();
			in.nextLine();

			parasito.setColor(color);
			
			controlador.getServParasito().modificarParasito(parasito);	
			System.out.println("¡Parásito modificado!");
			System.out.println();
		
	}

	private void registrarEnfermedad() {
		System.out.println("Introduzca los datos de la nueva enfermedad.");
		System.out.println();

		System.out.print("Nombre: ");
		in.nextLine();
		String nombre = in.nextLine();

		System.out.print("Síntomas: ");
		String sintomas = in.nextLine();


		boolean nociva = false;
		while (true) { 
			System.out.print("¿Es nociva para los humanos? (SI/NO): ");
		    String respuesta = in.nextLine().toUpperCase();
		    if (respuesta.equals("SI")) {
		    	nociva = true;
		        break;
		        } else if (respuesta.equals("NO")) {
		        	nociva = false;
		            break;
		        } else {
		            System.out.println("Por favor, responde con 'SI' o 'NO'.");
		        }
		    }

		    Enfermedad enfermedad = new Enfermedad();
		    enfermedad.setNombre(nombre);
		    enfermedad.setSintomas(sintomas);
		    enfermedad.setNociva(nociva);

		    List<Parasito> listaParasitos = new ArrayList<>();
		    while (true) {
		        System.out.print("¿La enfermedad es provocada por algún parásito? (SI/NO): ");
		        String respuesta = in.nextLine().toUpperCase();

		        if (respuesta.equals("NO")) {
		            System.out.println("No se han añadido parásitos a esta enfermedad.");
		            break;
		        } else if (respuesta.equals("SI")) {
		            boolean parasitoCorrecto = false;
		            
		            while (!parasitoCorrecto) {
		            	System.out.print("Introduzca el nombre del parásito: ");
		                String nombre_parasito = in.nextLine();

		                Parasito p = controlador.getServParasito().findByNombre(nombre_parasito);

		                if (p == null) {
		                    System.out.println("No existe ningún parásito con ese nombre. Inténtalo de nuevo.");
		                } else if (p.getEnfermedad() != null) {
		                    System.out.println("El parásito ya está asociado a otra enfermedad.");
		                } else {
		                    listaParasitos.add(p);
		                    parasitoCorrecto = true;
		                }
		            }

		            System.out.print("¿Quiere añadir otro parásito a esta enfermedad? (SI/NO): ");
		            String anadirotro = in.nextLine().toUpperCase();
		            if (anadirotro.equals("NO")) {
		                break;
		            }
		        } else {
		            System.out.println("Respuesta inválida. Introduce 'SI' o 'NO'.");
		        }
		    }

		    controlador.getServEnfermedad().insertarEnfermedad(enfermedad);
		    System.out.println("¡Enfermedad registrada con éxito!");
		    
		    if(listaParasitos != null) {
			    for (Parasito p : listaParasitos) {
			        p.setEnfermedad(enfermedad);
			        controlador.getServParasito().modificarParasito(p);
			    }	    	
		    }

		
	}
	
	

	/**
     * Registra una nueva persona en el sistema.
     * 
     * El usuario admin introduce los datos del nuevo usuario y este
     * valida que los datos no incluyan espacios ni que ya estén. 
     * Si los datos son válidos,
     * registra a la persona y sus credenciales. 
     */
	
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
