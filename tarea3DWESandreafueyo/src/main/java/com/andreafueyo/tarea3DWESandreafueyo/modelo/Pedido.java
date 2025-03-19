package com.andreafueyo.tarea3DWESandreafueyo.modelo;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name="pedidos")

public class Pedido implements Serializable{

	private static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDate fecha;

	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Ejemplar> ejemplares = new LinkedList<Ejemplar>();    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		String ret ="";
		ret ="EJEMPLAR";
		ret += "\tID: " + this.id;
		ret += "\tFecha: " + this.fecha;
		return ret;
	}


}
