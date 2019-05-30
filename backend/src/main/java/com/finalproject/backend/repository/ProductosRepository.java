package com.finalproject.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Productos;

@Repository("productosRepository")
public interface ProductosRepository extends JpaRepository<Productos, Serializable> {
	
	//METODO QUE DEVUELVE UNA LISTA DE PRODUCTOS QUE CONTENGAN EL PARAMETRO PASADO, YA SEA MINUSCULA O MAY
	public List<Productos> findByNombreContainingIgnoreCase(String nombre);
	
	///METODO QUE DEVUELVE UNA LISTA DE PRODUCTO QUE COMIENCEN POR EL PARAMETRO PASADO, YA SEA MINUSCULA O MAY
	public List<Productos> findByNombreStartingWithIgnoreCase(String nombre);

}
