package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreafueyo.tarea3DWESandreafueyo.repositorios.CredencialesRepository;

@Service
public class ServiciosCredenciales {
	
	@Autowired
	CredencialesRepository credencialesrepo;

	
}
