package com.api.crud.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.SociosModel;
import com.api.crud.repositories.ISociosRepository;

@Service
public class SociosService {

	@Autowired
	ISociosRepository sociosRepository;
	
	public ArrayList<SociosModel> getSocios(){
		return (ArrayList<SociosModel>) sociosRepository.findAll();
	}
	
	public SociosModel saveSocio(SociosModel socio) {
		return (SociosModel) sociosRepository.save(socio);
	}
	
	// Optional controla los null, si es null no devuelve nada
	public Optional<SociosModel> getById(Long socio_id){
		return this.sociosRepository.findById(socio_id);
	}
	
	public SociosModel updateById(SociosModel request, Long socio_id) {
	    if (socio_id == null) {
	        throw new IllegalArgumentException("El ID del socio no puede ser nulo");
	    }
	    
	    Optional<SociosModel> optionalSocio = this.sociosRepository.findById(socio_id);
	    
	    // Verifica si el socio con el ID proporcionado existe en la base de datos
	    if (optionalSocio.isPresent()) {
	        SociosModel socio = optionalSocio.get();
	        
	        socio.setNombre(request.getNombre());
	        socio.setApellido(request.getApellido());
	        socio.setDireccion(request.getDireccion());
	        socio.setTelefono(request.getTelefono());
	        socio.setEmail(request.getEmail());
	        
	        return sociosRepository.save(socio);
	    } else {
	        // Si el socio con el ID proporcionado no existe, lanza una excepción
	        throw new IllegalArgumentException("No se encontró ningún socio con el ID proporcionado: " + socio_id);
	    }
	}

	
	public Boolean deleteSocio(Long socio_id) {
		try {
			sociosRepository.deleteById(socio_id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
