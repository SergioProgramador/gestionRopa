package com.finalproject.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finalproject.backend.entity.Clientes;
import com.finalproject.backend.repository.ClientesRepository;
import com.finalproject.backend.service.ClientesService;

@Service("clientesService")
public class ClientesServiceImpl implements ClientesService{

	@Autowired
	@Qualifier("clientesRepository")
	private ClientesRepository clientesRepository;
	
	@Override
	public Clientes addClientes(Clientes clientes) {
		return clientesRepository.save(clientes);
	}

	@Override
	public List<Clientes> listAllClientes() {
		List<Clientes> clientes=clientesRepository.findAll();	
		return clientes;
	}

	@Override
	public Clientes findClientesById(int id) {		
		return clientesRepository.findById(id);
	}

	@Override
	public void removeClientes(int id) {
		Clientes clientes = findClientesById(id);
		if(null != clientes){
			clientesRepository.delete(clientes);
		}
		
	}

}
