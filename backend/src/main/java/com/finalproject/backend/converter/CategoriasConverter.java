package com.finalproject.backend.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.finalproject.backend.entity.Categorias;
import com.finalproject.backend.model.CategoriasModel;

@Component("categoriasConverter")
public class CategoriasConverter {

	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	//MODELO A ENTIDAD
	public Categorias convertModel2Entity(CategoriasModel categoriasModel) {
		Categorias categorias= dozer.map(categoriasModel, Categorias.class);
		return categorias;
	}
		
	//ENTIDAD A MODELO
	public CategoriasModel convertEntity2Model(Categorias categorias) {
		CategoriasModel categoriasModel= dozer.map(categorias, CategoriasModel.class);
		return categoriasModel;
	}
}
