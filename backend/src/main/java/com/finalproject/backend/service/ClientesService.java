package com.finalproject.backend.service;

import java.util.List;

import com.finalproject.backend.entity.Clientes;

public interface ClientesService {
	public abstract Clientes addClientes(Clientes clientes);
	public abstract List<Clientes> listAllClientes();
	public abstract Clientes findClientesById(int id);
	public abstract void removeClientes(int id);
}
