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

import com.finalproject.backend.entity.Categorias;
import com.finalproject.backend.service.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	@Qualifier("categoriasService")
	private CategoriasService categoriasService;
	
	//LISTAR TODAS LAS CATEGORIAS
	@CrossOrigin
	@GetMapping("/showcategorias")
    public List<Categorias> showCategorias(){
        return categoriasService.listAllCategorias();
    }
	
	//LISTAR UNA CATEGORIA
	@CrossOrigin
	@GetMapping("/{id}")
    public ResponseEntity<?> oneCategoria(@PathVariable(name="id") int id) {
		
		String id_str = String.valueOf(id);
		Categorias categoria = null;
		Map<String, Object> response = new HashMap<>();
		
		try {			
			categoria = categoriasService.findCategoriasById(id);			
		}catch(DataAccessException e) {
			response.put("info", "Error al realizar la consulta..");
			response.put("e", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(categoria == null) {
			response.put("info", "La categoria ID: ".concat(id_str.concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
    	return new ResponseEntity<Categorias>(categoria, HttpStatus.OK);
    }
	
	//BORRA UNA CATEGORIA
	@CrossOrigin
	@DeleteMapping("/removeCat/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable("id") int id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			categoriasService.removeCategorias(id);
		}catch(DataAccessException e){
			response.put("info", "Error al eliminar la categoria.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info", "La categoria se ha eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
    }
	
	//AÑADE UNA CATEGORIA
	@CrossOrigin
	@PostMapping("/add")
	public ResponseEntity<?> addCategoria(@RequestBody @Valid Categorias categorias) {
		
		Categorias categoria = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			categoria = categoriasService.addCategorias(categorias);
		}catch(DataAccessException e){
			response.put("info", "Error al añadir la categoria, intentalo de nuevo.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info","Se ha añadido correctamente la categoria.");
		response.put("categoria", categoria);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA UNA CATEGORIA
	@CrossOrigin
	@PutMapping("/updateCat/{id}")
	public ResponseEntity<?> updateCategoria(@PathVariable("id") int id, @RequestBody @Valid Categorias categorias) {
		
		String id_str = String.valueOf(id);
		Categorias currentCategoria = categoriasService.findCategoriasById(id);		
		Map<String, Object> response = new HashMap<>();		
		Categorias categoriaUpdated = null;
		
		if(currentCategoria == null) {
			response.put("info", "Error: no se puedo editar, la categoria ID:".concat(id_str.concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentCategoria.setNombre(categorias.getNombre());
			
			categoriaUpdated = categoriasService.addCategorias(currentCategoria);
			
		}catch(DataAccessException e) {
			response.put("info", "Error al actualizar la categoria.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info", "Se ha actualizado la categoria correctamente.");
		response.put("categoria", categoriaUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
