package com.finalproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finalproject.backend.entity.Categorias;
import com.finalproject.backend.repository.CategoriasRepository;
import com.finalproject.backend.service.CategoriasService;

@Service("categoriasService")
public class CategoriasServiceImpl implements CategoriasService{

	@Autowired
	@Qualifier("categoriasRepository")
	private CategoriasRepository categoriasRepository;
	
	@Override
	public Categorias addCategorias(Categorias categorias) {
		return categoriasRepository.save(categorias);
	}

	@Override
	public List<Categorias> listAllCategorias() {
		List<Categorias> categorias=categoriasRepository.findAll();	
		return categorias;
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

//	@Override
//	public CategoriasModel findCategoriasModelById(int id) {
//		return categoriasConverter.convertEntity2Model(findCategoriasById(id));
//	}

}
