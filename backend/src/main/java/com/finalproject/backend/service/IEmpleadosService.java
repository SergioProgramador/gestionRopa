package com.finalproject.backend.service;

import com.finalproject.backend.entity.Empleados;

public interface IEmpleadosService{

	public Empleados findByUsername(String username);
}
