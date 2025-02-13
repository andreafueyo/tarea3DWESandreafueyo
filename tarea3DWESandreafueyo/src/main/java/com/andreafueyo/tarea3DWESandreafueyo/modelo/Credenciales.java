package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="credenciales")
	public class Credenciales implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Id
		/*autoincrement*/
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column (unique = true)
		private String usuario;
		
		@Column 
		private String password;
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="idpersona", nullable = false)
		private Persona persona;

		
		
		public Credenciales() {
			this.persona = new Persona();
		}
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getUsuario() {
			return usuario;
		}
		
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public Persona getPersona() {
			return persona;
		}

		public void setPersona(Persona persona) {
			this.persona = persona;
		}
		
		@Override
		public String toString() {
		    String ret = "CREDENCIAL";
		    ret += "\tID: " + this.id;
		    ret += "\tUsuario: " + this.usuario;
		    ret += "\tPassword: " + this.password;
		    ret += "\tfk_persona: " + (this.persona != null ? this.persona.getId() : "null");
		    return ret;
		}

}
