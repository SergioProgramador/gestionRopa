package com.finalproject.backend.service;

import java.util.List;

import com.finalproject.backend.entity.Categorias;
import com.finalproject.backend.model.CategoriasModel;

public interface CategoriasService {
	public abstract CategoriasModel addCategorias(CategoriasModel categoriasModel);
	public abstract List<CategoriasModel> listAllCategorias();
	public abstract Categorias findCategoriasById(int id);
	public abstract void removeCategorias(int id);
	public abstract CategoriasModel findCategoriasModelById(int id);

}
