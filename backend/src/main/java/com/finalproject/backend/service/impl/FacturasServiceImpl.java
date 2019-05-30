package com.finalproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finalproject.backend.entity.Clientes;
import com.finalproject.backend.entity.Facturas;
import com.finalproject.backend.repository.FacturasRepository;
import com.finalproject.backend.service.FacturasService;

@Service("facturasService")
public class FacturasServiceImpl implements FacturasService{

	@Autowired
	@Qualifier("facturasRepository")
	private FacturasRepository facturasRepository;
	
	@Override
	public Facturas addFacturas(Facturas facturas) {	
		return facturasRepository.save(facturas);
	}

	@Override
	public List<Facturas> listAllFacturas() {
		List<Facturas> facturas=facturasRepository.findAll();	
		return facturas;
	}

	@Override
	public Facturas findFacturasById(int id) {	
		return facturasRepository.findById(id);
	}

	@Override
	public void removeFacturas(int id) {
		Facturas facturas = findFacturasById(id);
		if(null != facturas){
			facturasRepository.delete(facturas);
		}
		
	}

}
