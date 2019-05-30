package com.finalproject.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Facturas;

@Repository("facturasRepository")
public interface FacturasRepository extends JpaRepository<Facturas, Serializable> {
	
	public abstract Facturas findById(int id);
	public abstract Facturas findByTitulo(String titulo);
}
