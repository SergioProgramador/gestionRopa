package com.finalproject.backend.service;

import java.util.List;

import com.finalproject.backend.entity.Productos;

public interface ProductosService {
	
	public abstract Productos addProductos(Productos productos);
	public abstract List<Productos> listAllProductos();
	public abstract Productos findProductosById(int id);
	public abstract List<Productos> findProductosByNombre(String nombre);
	public abstract void removeProductos(int id);

}
