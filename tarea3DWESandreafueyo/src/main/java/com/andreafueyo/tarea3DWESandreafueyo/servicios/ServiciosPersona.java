package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PersonaRepository;

@Service
public class ServiciosPersona {
	@Autowired
	PersonaRepository personarepo;

}
