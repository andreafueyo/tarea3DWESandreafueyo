package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="mensajes")
public class Mensaje implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column 
	private LocalDateTime fechahora;
	
	@Column 
	private String mensaje;
	
	@ManyToOne
	@JoinColumn(name="idejemplar")
	private Ejemplar ejemplar;
	
	@ManyToOne
	@JoinColumn(name="idpersona")
	private Persona persona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}
	
	public Ejemplar getEjemplar() {
		return ejemplar;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	@Override
	public String toString() {
		String ret ="";
		ret ="MENSAJE";
		ret += "\tID: " + this.id;
		ret += "\tFecha y hora: " + this.fechahora;
		ret += "\tMensaje: " + this.mensaje;
		ret += "\tfk_personasMensajes: " + this.persona.getId();
		ret += "\tfk_ejemplaresMensajes: " + this.ejemplar.getId();
		return ret;
	}
		
}
