package com.finalproject.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Categorias;

@Repository("categoriasRepository")
public interface CategoriasRepository extends JpaRepository<Categorias, Serializable>{
	
	public abstract Categorias findById(int id);
	public abstract Categorias findByNombre(String nombre);

}
