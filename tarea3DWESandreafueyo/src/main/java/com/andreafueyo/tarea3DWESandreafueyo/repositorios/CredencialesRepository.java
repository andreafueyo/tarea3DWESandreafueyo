package com.andreafueyo.tarea3DWESandreafueyo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Credenciales;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales, Long>{

}
