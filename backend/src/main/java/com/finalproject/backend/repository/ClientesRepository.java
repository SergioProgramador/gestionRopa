package com.finalproject.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Clientes;

@Repository("clientesRepository")
public interface ClientesRepository extends JpaRepository<Clientes, Serializable>{
	
	public abstract Clientes findById(int id);
	public abstract Clientes findByNombre(String nombre);

}
