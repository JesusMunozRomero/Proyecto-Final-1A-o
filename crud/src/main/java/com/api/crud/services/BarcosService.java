package com.api.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.BarcosModel;
import com.api.crud.repositories.IBarcosRepository;

@Service
public class BarcosService {

	@Autowired
	IBarcosRepository BarcosRepository;
	
	public List<BarcosModel> getBarcos(){
		return BarcosRepository.findAll();
	}
	
	public BarcosModel saveBarco(BarcosModel barco) {
		return (BarcosModel) BarcosRepository.save(barco);
	}
	
	// Optional controla los null, si es null no devuelve nada
	public Optional<BarcosModel> getById(Long barco_id){
		return this.BarcosRepository.findById(barco_id);
	}
	
	public BarcosModel updateById(BarcosModel request, Long barco_id) {
	    if (barco_id == null) {
	        throw new IllegalArgumentException("El ID del barco no puede ser nulo");
	    }
	    
	    Optional<BarcosModel> optionalBarco = BarcosRepository.findById(barco_id);
	    
	    // Verifica si el barco con el ID proporcionado existe en la base de datos
	    if (optionalBarco.isPresent()) {
	        BarcosModel barco = optionalBarco.get();
	        
	        barco.setSocio_id(request.getSocio_id());
	        barco.setNumero_matricula(request.getNumero_matricula());
	        barco.setNombre_barco(request.getNombre_barco());
	        barco.setNumero_amarre(request.getNumero_amarre());
	        barco.setCuota_amarre(request.getCuota_amarre());
	        
	        return BarcosRepository.save(barco);
	    } else {
	        throw new IllegalArgumentException("No se encontró ningún barco con el ID proporcionado: " + barco_id);
	    }
	}

	
	public Boolean deleteBarco(Long barco_id) {
		try {
			BarcosRepository.deleteById(barco_id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
