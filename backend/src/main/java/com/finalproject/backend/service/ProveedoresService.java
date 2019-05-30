package com.finalproject.backend.service;

import java.util.List;

import com.finalproject.backend.entity.Proveedores;

public interface ProveedoresService {
	public abstract Proveedores addProveedor(Proveedores proveedores);
	public abstract List<Proveedores> listAllProveedores();
	public abstract Proveedores findProveedorById(int id);
	public abstract void removeProveedor(int id);
}
