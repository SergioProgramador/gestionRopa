package com.finalproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finalproject.backend.entity.Productos;
import com.finalproject.backend.repository.ProductosRepository;
import com.finalproject.backend.service.ProductosService;

@Service("productosService")
public class ProductosServiceImpl implements ProductosService{
	
	@Autowired
	@Qualifier("productosRepository")
	private ProductosRepository productosRepository; 

	@Override
	public Productos addProductos(Productos productos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Productos> listAllProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Productos findProductosById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Productos> findProductosByNombre(String nombre) {
		return productosRepository.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	public void removeProductos(int id) {
		// TODO Auto-generated method stub
		
	}

}
