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

import com.finalproject.backend.entity.Clientes;
import com.finalproject.backend.service.ClientesService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	@Qualifier("clientesService")
	private ClientesService clientesService;

	// LISTAR TODOS LAS CLIENTES
	@CrossOrigin
	@GetMapping("/showclientes")
	public List<Clientes> showClientes() {
		return clientesService.listAllClientes();
	}

	// LISTAR UN CLIENTE1
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<?> oneCliente(@PathVariable(name = "id") int id) {

		String id_str = String.valueOf(id);
		Clientes clientes = null;
		Map<String, Object> response = new HashMap<>();

		try {
			clientes = clientesService.findClientesById(id);
		} catch (DataAccessException e) {
			response.put("info", "Error al realizar la consulta.");
			response.put("e", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (clientes == null) {
			response.put("info", "El cliente ID: ".concat(id_str.concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Clientes>(clientes, HttpStatus.OK);
	}

	// BORRA UN CLIENTE
	@CrossOrigin
	@DeleteMapping("/removeCliente/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable("id") int id) {

		Map<String, Object> response = new HashMap<>();

		try {
			clientesService.removeClientes(id);
		} catch (DataAccessException e) {
			response.put("info", "Error al eliminar el cliente.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("info", "El cliente se ha eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// AÑADE UN CLIENTE
	@CrossOrigin
	@PostMapping("/addCliente")
	public ResponseEntity<?> addCliente(@RequestBody @Valid Clientes clientes) {

		Clientes cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clientesService.addClientes(clientes);
		} catch (DataAccessException e) {
			response.put("info", "Error al añadir el cliente, intentalo de nuevo.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("info", "Se ha añadido el cliente correctamente.");
		response.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// ACTUALIZA UN PROVEEDOR
	@CrossOrigin
	@PutMapping("/updateCliente/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable("id") int id, @RequestBody @Valid Clientes clientes) {

		String id_str = String.valueOf(id);
		Clientes currentCliente = clientesService.findClientesById(id);
		Map<String, Object> response = new HashMap<>();
		Clientes clienteUpdated = null;

		if (currentCliente == null) {
			response.put("info",
					"Error: no se puedo editar, el cliente ID:".concat(id_str.concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentCliente.setNombre(clientes.getNombre());
			currentCliente.setApellidos(clientes.getApellidos());
			currentCliente.setDireccion(clientes.getDireccion());
			currentCliente.setCodigo_postal(clientes.getCodigo_postal());
			currentCliente.setEmail(clientes.getEmail());
			currentCliente.setCiudad(clientes.getCiudad());
			currentCliente.setMovil(clientes.getMovil());
			currentCliente.setSexo(clientes.getSexo());
			currentCliente.setCreate_time(clientes.getCreate_time());
			currentCliente.setUpdate_time(clientes.getUpdate_time());

			clienteUpdated = clientesService.addClientes(currentCliente);

		} catch (DataAccessException e) {
			response.put("info", "Error al actualizar el cliente.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("info", "Se ha actualizado el cliente correctamente.");
		response.put("cliente", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
