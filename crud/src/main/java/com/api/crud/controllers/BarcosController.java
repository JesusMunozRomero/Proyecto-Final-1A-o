package com.api.crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.crud.models.BarcosModel;
import com.api.crud.models.SociosModel;
import com.api.crud.services.BarcosService;
import com.api.crud.services.SociosService;

@RestController
@RequestMapping("/Barcos")
public class BarcosController {

	@Autowired
	private BarcosService BarcosService;
	
	@GetMapping
	public List<BarcosModel> getBarcos(){
		return this.BarcosService.getBarcos();
	}
	
	@PostMapping
	public BarcosModel saveBarco(@RequestBody BarcosModel barco) {
		return (BarcosModel) BarcosService.saveBarco(barco);
	}
	
	@GetMapping(path="/{barco_id}")
	public Optional<BarcosModel> getBarcoById(@PathVariable("barco_id") long barco_id){
		return this.BarcosService.getById(barco_id);
	}
	
	@PutMapping(path="/{barco_id}")
	public BarcosModel updateBarcoById(@RequestBody BarcosModel request,@PathVariable("barco_id")  Long barco_id) {
		return this.BarcosService.updateById(request, barco_id);
	}
	
	@DeleteMapping(path="/{barco_id}")
	public String deleteById(@PathVariable("barco_id") Long barco_id) {
		boolean ok=this.BarcosService.deleteBarco(barco_id);
		
		if(ok){
		return "Barco con id " + barco_id +" eliminado";
		}else {
		return "Error, hubo un problema, no se puede eliminar el barco "+barco_id;
		}
	}
}
