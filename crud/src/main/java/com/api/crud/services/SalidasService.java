package com.api.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.BarcosModel;
import com.api.crud.models.SalidasModel;
import com.api.crud.repositories.IBarcosRepository;
import com.api.crud.repositories.ISalidasRepository;

@Service
public class SalidasService {

	@Autowired
	ISalidasRepository SalidasRepository;
	
	public List<SalidasModel> getSalidas(){
		return SalidasRepository.findAll();
	}
	
	public SalidasModel saveSalida(SalidasModel salida) {
		return (SalidasModel) SalidasRepository.save(salida);
	}
	
	// Optional controla los null, si es null no devuelve nada
	public Optional<SalidasModel> getById(Long salida_id){
		return this.SalidasRepository.findById(salida_id);
	}
	
	public SalidasModel updateById(SalidasModel request, Long salida_id) {
	    if (salida_id == null) {
	        throw new IllegalArgumentException("El ID de la salida no puede ser nulo");
	    }
	    
	    Optional<SalidasModel> optionalSalidaId = SalidasRepository.findById(salida_id);
	    Optional<SalidasModel> optionalSalidaPatron = SalidasRepository.findById(optionalSalidaId.get().getPatron_id());
	    
	    // Verifica si la salida con el id y id_patron proporcionado existe en la base de datos
	    if (optionalSalidaId.isPresent() && optionalSalidaPatron.isPresent()) {
	        SalidasModel salida = optionalSalidaId.get();
	        
	        salida.setBarco_id(request.getBarco_id());
	        salida.setFecha_salida(request.getFecha_salida());
	        salida.setHora_salida(request.getHora_salida());
	        salida.setDestino(request.getDestino());
	        salida.setPatron_id(request.getPatron_id());
	        
	        return SalidasRepository.save(salida);
	    } else {
	        throw new IllegalArgumentException("No se encontró ninguna salida con el ID proporcionado: " + salida_id);
	    }
	}

	
	public Boolean deleteSalida(Long salida_id) {
		try {
			SalidasRepository.deleteById(salida_id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
