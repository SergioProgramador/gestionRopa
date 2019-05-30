package com.finalproject.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="roles", uniqueConstraints = @UniqueConstraint(columnNames= {"role", "username"}))
public class Roles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_roles", unique=true, nullable=false) //le ponemos validaciones (que es unico y no puede ser nulo)
	private int id_roles;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="username", nullable=false)
	private Empleados empleado;
	
	@Column(name="role", nullable=false, length=50) //un usuario siempre tiene que tener un rol
	private String role;

	//CONSTRUCTOR
	public Roles(int id_roles, Empleados empleado, String role) {
		super();
		this.id_roles = id_roles;
		this.empleado = empleado;
		this.role = role;
	}
	
	public Roles() {
		
	}
	
	//GETTERS AND SETTERS
	public int getId_roles() {
		return id_roles;
	}

	public void setId_roles(int id_roles) {
		this.id_roles = id_roles;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
}
