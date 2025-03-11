package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="ejemplares")

public class Ejemplar implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="idplanta")
	private Planta planta;
	
	@ManyToOne
	@JoinColumn(name="idpedido")
	private Pedido pedido;
	
	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL)
	private List<Mensaje> mensajes = new LinkedList<Mensaje>();	
	
	@Column
	private boolean disponible;
	
	public Ejemplar() {}
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		String ret ="";
		ret ="EJEMPLAR";
		ret += "\tID: " + this.id;
		ret += "\tNombre: " + this.nombre;
		ret += "\tfk_planta " + this.planta.getId();
		return ret;
	}
	
	
}
