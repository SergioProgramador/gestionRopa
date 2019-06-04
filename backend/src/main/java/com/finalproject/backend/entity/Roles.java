package com.finalproject.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id", nullable=false)
	private int role_id;

	@Column(name="role", unique=true, nullable=false, length=30) //un usuario siempre tiene que tener un rol
	private String role;

	
	//CONSTRUCTORS
	public Roles(){
		
	}
	
	public Roles(int role_id, String role) {
		super();
		this.role_id = role_id;
		this.role = role;
	}

	//GETTERS AND SETTERS

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
	
}
