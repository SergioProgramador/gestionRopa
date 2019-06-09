package com.finalproject.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.backend.entity.Empleados;
import com.finalproject.backend.service.impl.EmpleadoServiceImpl;

@RestController
@RequestMapping("/empleados")
public class EmpleadosController {

	@Autowired
	@Qualifier("empleadoService")
	private EmpleadoServiceImpl empleadoService;
	
	// LISTAR TODOS LOS CLIENTES
	@CrossOrigin
	@GetMapping("/showempleados")
	public Iterable<Empleados> showEmpleados() {
		return empleadoService.listAllEmpleados();
	}
	
	// AÑADE UN EMPLEADO
	@CrossOrigin
	@PostMapping("/addEmpleado")
	public ResponseEntity<?> addEmpleado(@RequestBody @Valid Empleados empleados) {

		Map<String, Object> response = new HashMap<>();

		if(null == empleadoService.findByUsername(empleados.getUsername())) {
			empleadoService.addEmpleado(empleados);
			response.put("info", "Se ha añadido el empleado correctamente.");
			response.put("empleado", empleados);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}else {
			response.put("info", "El empleado ya existe con este username!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FOUND);
		}

		
	}
	
	//BORRA UN EMPLEADO
	@CrossOrigin
	@DeleteMapping("/remove/{username}")
    public ResponseEntity<?> deleteCategoria(@PathVariable("username") String username) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoService.removeEmpleados(username);
		}catch(DataAccessException e){
			response.put("info", "Error al eliminar el empleado.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info", "El empleado se ha eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
    }
	
}
