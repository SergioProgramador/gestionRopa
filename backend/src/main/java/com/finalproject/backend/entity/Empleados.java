package com.finalproject.backend.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="empleados")
public class Empleados {
	
	@Id
	@Column(name="username", unique=true, nullable=false, length=100)
	private String username;
	
	@Column(name="password", nullable=false, length=50)
	private String password;
	
	@Column(name="nombre", nullable=false, length=50)
	private String nombre;
	
	@Column(name="apellidos", nullable=false, length=100)
	private String apellidos;
	
	@Column(name = "direccion", nullable=false, length=100)
	private String direccion;
	
	@Column(name="email", nullable=false, length=70)
	private String email;
	
	@Column(name="telefono", nullable=false, length=9)
	private String telefono;
	
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_time;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_time;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="empleado")
	private Set<Roles> roles = new HashSet<Roles>();
	
	//CONSTRUCTOR
	public Empleados() {
		
	}

	public Empleados(String username, String password, String nombre, String apellidos, String direccion, String email,
			String telefono, Date create_time, Date update_time, Set<Roles> roles) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.create_time = create_time;
		this.update_time = update_time;
		this.roles = roles;
	}
	
	//GETTERS AND SETTERS
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	
	

}
