package com.finalproject.backend.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.backend.entity.Proveedores;
import com.finalproject.backend.service.ProveedoresService;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {
	
	@Autowired
	@Qualifier("proveedoresService")
	private ProveedoresService proveedoresService;
	
	//LISTAR TODOS LAS PROVEEDORES
	@CrossOrigin
	@GetMapping("/showproveedores")
    public List<Proveedores> showProveedores(){
        return proveedoresService.listAllProveedores();
    }
	
	//LISTAR UNA CATEGORIA
	@CrossOrigin
	@GetMapping("/{id}")
    public ResponseEntity<?> oneProveedor(@PathVariable(name="id") int id) {
		
		String id_str = String.valueOf(id);
		Proveedores proveedor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {			
			proveedor = proveedoresService.findProveedorById(id);			
		}catch(DataAccessException e) {
			response.put("info", "Error al realizar la consulta.");
			response.put("e", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(proveedor == null) {
			response.put("info", "El proveedor ID: ".concat(id_str.concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
    	return new ResponseEntity<Proveedores>(proveedor, HttpStatus.OK);
    }
	
	//BORRA UNA CATEGORIA
	@CrossOrigin
	@DeleteMapping("/removeProveedor/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable("id") int id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			proveedoresService.removeProveedor(id);
		}catch(DataAccessException e){
			response.put("info", "Error al eliminar el proveedor.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info", "El proveedor se ha eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
    }
	
	//AÑADE UN PROVEEDOR
	@CrossOrigin
	@PostMapping("/addProveedor")
	public ResponseEntity<?> addProveedor(@RequestBody @Valid Proveedores proveedores) {
		
		Proveedores proveedor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			proveedor = proveedoresService.addProveedor(proveedores);
		}catch(DataAccessException e){
			response.put("info", "Error al añadir el proveedor, intentalo de nuevo.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info","Se ha añadido el proveedor correctamente.");
		response.put("proveedor", proveedor);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA UN PROVEEDOR
	@CrossOrigin
	@PutMapping("/updateProveedor/{id}")
	public ResponseEntity<?> updateProveedor(@PathVariable("id") int id, @RequestBody @Valid Proveedores proveedores) {
		
		String id_str = String.valueOf(id);
		Proveedores currentProveedor = proveedoresService.findProveedorById(id);		
		Map<String, Object> response = new HashMap<>();		
		Proveedores proveedorUpdated = null;
		
		if(currentProveedor == null) {
			response.put("info", "Error: no se puedo editar, el proveedor ID:".concat(id_str.concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentProveedor.setEmpresa(proveedores.getEmpresa());
			currentProveedor.setDescripcion(proveedores.getDescripcion());
			currentProveedor.setDireccion(proveedores.getDireccion());
			currentProveedor.setTelefono(proveedores.getTelefono());
			currentProveedor.setEmail(proveedores.getEmail());
			currentProveedor.setFrecuencia(proveedores.getFrecuencia());
			currentProveedor.setEstado_pago(proveedores.getEstado_pago());
			currentProveedor.setCantidad(proveedores.getCantidad());
			currentProveedor.setCreate_time(proveedores.getCreate_time());
			currentProveedor.setUpdate_time(proveedores.getUpdate_time());
			
			proveedorUpdated = proveedoresService.addProveedor(currentProveedor);
			
		}catch(DataAccessException e) {
			response.put("info", "Error al actualizar el proveedor.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info", "Se ha actualizado el proveedor correctamente.");
		response.put("proveedor", proveedorUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
