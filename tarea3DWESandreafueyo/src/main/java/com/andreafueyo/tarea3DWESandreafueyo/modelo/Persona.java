package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

	@Entity
	@Table(name="personas")

	public class Persona implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Id
		/*autoincrement*/
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column
		private String nombre;
		
		@Column (unique = true)
		private String email;
		
		@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
	    private Credenciales credenciales;
		
		public Persona() {}
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public Credenciales getCredenciales() { return credenciales; }
	    public void setCredenciales(Credenciales credenciales) { this.credenciales = credenciales; }
	
		@Override
		public String toString() {
			String ret ="";
			ret ="PERSONA";
			ret += "\tID: " + this.id;
			ret += "\tNombre: " + this.nombre;
			ret += "\tCorreo electrónico " + this.email;
			return ret;
		}
}
