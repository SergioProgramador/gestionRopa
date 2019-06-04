package com.finalproject.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Empleados;


@Repository("empleadosRepository")
public interface EmpleadosRepository extends CrudRepository<Empleados, Serializable>{
	
	public abstract Empleados findByUsername(String username); //con este metodo encontraremos a un empleado por su username.
	
	@Query("select e from Empleados e where e.username=?1")
	public Empleados findByUsername2(String username);
	
	

}
