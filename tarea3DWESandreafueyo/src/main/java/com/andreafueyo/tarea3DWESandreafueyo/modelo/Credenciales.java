package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
		
		
		public Credenciales() {}
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getUsuario() {
			return usuario;
		}
		
		public void setNombre(String password) {
			this.password = password;
	}
}
