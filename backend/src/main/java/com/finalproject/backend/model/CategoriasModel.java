package com.finalproject.backend.model;

public class CategoriasModel {
	
	private int id;
	private String nombre;
	
	//METODO TOSTRING
	@Override
	public String toString() {
		return "CategoriasModel [id=" + id + ", nombre=" + nombre + "]";
	}
	
	//CONSTRUCTOR
	public CategoriasModel(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public CategoriasModel() {	
	}
	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
