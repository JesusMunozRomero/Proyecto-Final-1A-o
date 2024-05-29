package com.api.crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.crud.models.BarcosModel;
import com.api.crud.models.SalidasModel;
import com.api.crud.models.SociosModel;
import com.api.crud.services.BarcosService;
import com.api.crud.services.SalidasService;
import com.api.crud.services.SociosService;

@RestController
@RequestMapping("/Salidas")
public class SalidasController {

	@Autowired
	private SalidasService SalidasService;
	
	@GetMapping
	public List<SalidasModel> getSalidas(){
		return this.SalidasService.getSalidas();
	}
	
	@PostMapping
	public SalidasModel saveSalida(@RequestBody SalidasModel salida) {
		return SalidasService.saveSalida(salida);
	}
	
	@GetMapping(path="/{salida_id}")
	public Optional<SalidasModel> getSalidaById(@PathVariable("salida_id") long salida_id){
		return this.SalidasService.getById(salida_id);
	}
	
	@PutMapping(path="/{salida_id}")
	public SalidasModel updateSalidaById(@RequestBody SalidasModel request,@PathVariable("salida_id")  Long salida_id) {
		return this.SalidasService.updateById(request, salida_id);
	}
	
	@DeleteMapping(path="/{salida_id}")
	public String deleteById(@PathVariable("salida_id") Long salida_id) {
		boolean ok=this.SalidasService.deleteSalida(salida_id);
		
		if(ok){
		return "salida con id " + salida_id +" eliminada";
		}else {
		return "Error, hubo un problema, no se puede eliminar la salida "+salida_id;
		}
	}
}
