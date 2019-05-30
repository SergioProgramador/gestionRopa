package com.finalproject.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Proveedores;

@Repository("proveedoresRepository")
public interface ProveedoresRepository extends JpaRepository<Proveedores, Serializable>{
	
	public abstract Proveedores findById(int id);
	public abstract Proveedores findByEmpresa(String empresa);


}
