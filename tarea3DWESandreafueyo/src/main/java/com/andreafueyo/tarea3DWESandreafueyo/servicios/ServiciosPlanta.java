package com.andreafueyo.tarea3DWESandreafueyo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager;

import com.andreafueyo.tarea3DWESandreafueyo.modelo.Enfermedad;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Parasito;
import com.andreafueyo.tarea3DWESandreafueyo.modelo.Planta;
import com.andreafueyo.tarea3DWESandreafueyo.repositorios.PlantaRepository;
 
@Service
public class ServiciosPlanta {

	@Autowired
	private PlantaRepository plantarepo;
	
	public boolean validarPlanta(Planta p) {
		if (plantarepo.existeCodigo(p)) {
			return false;
		}
		return true;
	}
	

	public void insertarPlanta(Planta p) {
		Planta planta =  plantarepo.saveAndFlush(p);
	}
//	public void insertarPlanta(Planta p, List<Enfermedad> listaEnfermedades) {
//		p.getEnfermedades().addAll(listaEnfermedades);
//		Planta planta =  plantarepo.saveAndFlush(p);
//	}
//	
	public Planta modificar(Planta p) {
		if (!plantarepo.existeCodigo(p)) {
			return null;
		}
		return plantarepo.saveAndFlush(p);
	}

	public Planta findByCod(String cod) {
		return plantarepo.findByCod(cod);
	}
	
	public List<Planta> verPlantas() {
		return plantarepo.findAllByOrderByCodigoAsc();
	}


	public String verPlantaDetalle(Planta p) {
//
		String ret = "";
		ret += p.toString();
//		
//		List<Enfermedad> listaEnfermedades = enfermedadrepo.findEnfermedadesByPlantaId(p.getId());
//		if(!listaEnfermedades.isEmpty()) {
//			for(Enfermedad e : listaEnfermedades) {
//				ret += "\n" +e.toString();
//				List<Parasito> listaParasitos = pararepo.findParasitosByEnfermedadId(e.getId());
//				if(!listaParasitos.isEmpty()) {
//					for(Parasito par : listaParasitos) {
//						ret += "\n" +par.toString();
//					}
//				}
//			}
//		}
//		
		return ret;		
	}
}
