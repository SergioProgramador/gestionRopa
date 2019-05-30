package com.finalproject.backend.service;

import java.util.List;

import com.finalproject.backend.entity.Categorias;

public interface CategoriasService {
	public abstract Categorias addCategorias(Categorias categorias);
	public abstract List<Categorias> listAllCategorias();
	public abstract Categorias findCategoriasById(int id);
	public abstract void removeCategorias(int id);
	//public abstract CategoriasModel findCategoriasModelById(int id);

}
