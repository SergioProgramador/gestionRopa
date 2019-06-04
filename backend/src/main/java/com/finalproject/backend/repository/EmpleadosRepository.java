package com.finalproject.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Empleados;


@Repository("empleadosRepository")
public interface EmpleadosRepository extends JpaRepository<Empleados, Serializable>{
	
	public abstract Empleados findByUsername(String username); //con este metodo encontraremos a un empleado por su username.

}
