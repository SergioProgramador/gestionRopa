package com.finalproject.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finalproject.backend.converter.CategoriasConverter;
import com.finalproject.backend.entity.Categorias;
import com.finalproject.backend.model.CategoriasModel;
import com.finalproject.backend.repository.CategoriasRepository;
import com.finalproject.backend.service.CategoriasService;

@Service("categoriasService")
public class CategoriasServiceImpl implements CategoriasService{

	@Autowired
	@Qualifier("categoriasRepository")
	private CategoriasRepository categoriasRepository;
	
	@Autowired
	@Qualifier("categoriasConverter")
	private CategoriasConverter categoriasConverter;
	
	@Override
	public CategoriasModel addCategorias(CategoriasModel categoriasModel) {
		Categorias categorias=categoriasRepository.save(categoriasConverter.convertModel2Entity(categoriasModel)); 
		return categoriasConverter.convertEntity2Model(categorias);
	}

	@Override
	public List<CategoriasModel> listAllCategorias() {
		List<Categorias> categorias=categoriasRepository.findAll();
		List<CategoriasModel> categoriasModel =new ArrayList<CategoriasModel>();
		for(Categorias categoria : categorias) {
			categoriasModel.add(categoriasConverter.convertEntity2Model(categoria));
		}		
		return categoriasModel;
	}

	@Override
	public Categorias findCategoriasById(int id) {
		return categoriasRepository.findById(id);
	}

	@Override
	public void removeCategorias(int id) {
		Categorias categorias = findCategoriasById(id);
		if(null != categorias){
			categoriasRepository.delete(categorias);
		}	
	}

	@Override
	public CategoriasModel findCategoriasModelById(int id) {
		return categoriasConverter.convertEntity2Model(findCategoriasById(id));
	}

}
