package com.finalproject.backend.service;

import java.util.List;

import com.finalproject.backend.entity.Facturas;

public interface FacturasService {
	public abstract Facturas addFacturas(Facturas facturas);
	public abstract List<Facturas> listAllFacturas();
	public abstract Facturas findFacturasById(int id);
	public abstract void removeFacturas(int id);
}
