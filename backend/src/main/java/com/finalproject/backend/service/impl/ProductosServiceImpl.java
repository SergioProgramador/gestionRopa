package com.finalproject.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finalproject.backend.entity.Categorias;
import com.finalproject.backend.entity.Productos;
import com.finalproject.backend.entity.Proveedores;
import com.finalproject.backend.repository.CategoriasRepository;
import com.finalproject.backend.repository.ProductosRepository;
import com.finalproject.backend.service.ProductosService;
import com.finalproject.backend.service.ProveedoresService;

@Service("productosService")
public class ProductosServiceImpl implements ProductosService{
	
	@Autowired
	@Qualifier("productosRepository")
	private ProductosRepository productosRepository; 
	
	@Autowired
	@Qualifier("categoriasRepository")
	private CategoriasRepository categoriasRepository; 
	
	@Autowired
	@Qualifier("proveedoresService")
	private ProveedoresService proveedoresService; 
	
	

	@Override
	public Productos addProductos(Productos productos) {
		return productosRepository.save(productos);
	}

	@Override
	public List<Productos> listAllProductos() {
		List<Productos> productos=productosRepository.findAll();	
		return productos;
	}

	@Override
	public Productos findProductosById(int id) {
		return productosRepository.findById(id);
	}

	@Override
	public List<Productos> findProductosByNombre(String nombre) {
		return productosRepository.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	public void removeProductos(int id) {
		Productos productos = findProductosById(id);
		if(null != productos){
			productosRepository.delete(productos);
		}
		
	}

}
