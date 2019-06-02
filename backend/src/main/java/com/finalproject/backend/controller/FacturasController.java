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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.backend.entity.Facturas;
import com.finalproject.backend.entity.Productos;
import com.finalproject.backend.service.FacturasService;
import com.finalproject.backend.service.ProductosService;

@RestController
@RequestMapping("/facturas")
public class FacturasController {
	
	@Autowired
	@Qualifier("facturasService")
	private FacturasService facturasService;
	
	@Autowired
	@Qualifier("productosService")
	private ProductosService productosService;
	
	//LISTAR UNA FACTURA
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<?> oneFactura(@PathVariable(name = "id") int id) {

		String id_str = String.valueOf(id);
		Facturas facturas = null;
		Map<String, Object> response = new HashMap<>();

		try {
			facturas = facturasService.findFacturasById(id);
		} catch (DataAccessException e) {
			response.put("info", "Error al realizar la consulta.");
			response.put("e", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (facturas == null) {
			response.put("info", "La factura ID: ".concat(id_str.concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Facturas>(facturas, HttpStatus.OK);
	}
	
	// BORRA UNA FACTURA
	@CrossOrigin
	@DeleteMapping("/removeFactura/{id}")
	public ResponseEntity<?> deleteFactura(@PathVariable("id") int id) {
	
		Map<String, Object> response = new HashMap<>();
	
		try {
			facturasService.removeFacturas(id);
		} catch (DataAccessException e) {
			response.put("info", "Error al eliminar la factura.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		response.put("info", "La factura se ha eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//CREA UNA FACTURA
	@CrossOrigin
	@PostMapping("/addFactura")
	public ResponseEntity<?> addFactura(@RequestBody @Valid Facturas facturas) {

		Facturas factura = null;
		Map<String, Object> response = new HashMap<>();

		try {
			factura = facturasService.addFacturas(facturas);
		} catch (DataAccessException e) {
			response.put("info", "Error al añadir la factura, intentalo de nuevo.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("info", "Se ha añadido la factura correctamente.");
		response.put("factura", factura);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	//DEVUELVE LA LISTA DE PRODUCTOS FILTRADOS POR EL "NOMBRE"
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/productosFiltrados/{nombre}")
	public List<Productos> filtrarProductos(@PathVariable String nombre){
		return productosService.findProductosByNombre(nombre);
		
	}



}
