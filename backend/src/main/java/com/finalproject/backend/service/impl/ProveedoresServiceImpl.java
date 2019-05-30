package com.finalproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finalproject.backend.entity.Proveedores;
import com.finalproject.backend.repository.ProveedoresRepository;
import com.finalproject.backend.service.ProveedoresService;

@Service("proveedoresService")
public class ProveedoresServiceImpl implements ProveedoresService{

	@Autowired
	@Qualifier("proveedoresRepository")
	private ProveedoresRepository proveedoresRepository;
	
	@Override
	public Proveedores addProveedor(Proveedores proveedores) {
		return proveedoresRepository.save(proveedores);
	}

	@Override
	public List<Proveedores> listAllProveedores() {
		List<Proveedores> proveedores=proveedoresRepository.findAll();	
		return proveedores;
	}

	@Override
	public Proveedores findProveedorById(int id) {
		return proveedoresRepository.findById(id);
	}

	@Override
	public void removeProveedor(int id) {
		Proveedores proveedor = findProveedorById(id);
		if(null != proveedor){
			proveedoresRepository.delete(proveedor);
		}
		
	}

}
