package com.finalproject.backend.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.backend.converter.CategoriasConverter;
import com.finalproject.backend.entity.Categorias;
import com.finalproject.backend.model.CategoriasModel;
import com.finalproject.backend.service.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	@Qualifier("categoriasService")
	private CategoriasService categoriasService;
	
	@Autowired
	@Qualifier("categoriasConverter")
	private CategoriasConverter categoriasConverter;
	
	//LISTAR TODAS LAS CATEGORIAS
	@CrossOrigin
	@GetMapping("/showcategorias")
    public List<CategoriasModel> showCategorias(){
        return categoriasService.listAllCategorias();
    }
	
	//LISTAR UNA CATEGORIA
	@CrossOrigin
	@GetMapping("/{id}")
    public CategoriasModel oneCategoria(@PathVariable(name="id") int id) {
    	return categoriasService.findCategoriasModelById(id);
    }
	
	//BORRA UNA CATEGORIA
	@CrossOrigin
	//@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@DeleteMapping("/removeCat/{id}")
    public void deleteCategoria(@PathVariable("id") int id) throws IOException {
		categoriasService.removeCategorias(id);
    }
	
	//AÑADE UNA CATEGORIA
	@CrossOrigin
	@PostMapping("/add")
	public CategoriasModel addCategoria(@RequestBody @Valid Categorias categorias) {
		return categoriasService.addCategorias(categoriasConverter.convertEntity2Model(categorias));
	}
	
	//ACTUALIZA UNA CATEGORIA
	@CrossOrigin
	@PutMapping("/updateCat/{id}")
	public CategoriasModel updateCategoria(@PathVariable("id") int id, @RequestBody @Valid Categorias categorias) {
		Categorias currentCategoria = categoriasService.findCategoriasById(id);
		currentCategoria.setId(categorias.getId());
		currentCategoria.setNombre(categorias.getNombre());
		return categoriasService.addCategorias(categoriasConverter.convertEntity2Model(categorias));
	}

}
