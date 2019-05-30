package com.finalproject.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "clientes")
public class Clientes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "codigo_postal")
	private String codigo_postal;

	@Column(name = "ciudad")
	private String ciudad;

	@Column(name = "email")
	private String email;

	@Column(name = "movil")
	private String movil;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_time;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_time;
	
	@JsonIgnoreProperties({"cliente_id" , "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente_id", cascade=CascadeType.ALL)
	private List<Facturas> facturas;
	
	@PrePersist
	public void prePersist(){
		this.create_time = new Date();
	}

	// CONSTRUCTOR
	public Clientes(int id, String nombre, String apellidos, String direccion, String codigo_postal, String ciudad,
			String email, String movil, String sexo, Date create_time, Date update_time, List<Facturas> facturas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.codigo_postal = codigo_postal;
		this.ciudad = ciudad;
		this.email = email;
		this.movil = movil;
		this.sexo = sexo;
		this.create_time = create_time;
		this.update_time = update_time;
		this.facturas = facturas;
	}

	public Clientes() {
		this.facturas = new ArrayList<>();
	}

	// TOSTRING	
	@Override
	public String toString() {
		return "Clientes [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", codigo_postal=" + codigo_postal + ", ciudad=" + ciudad + ", email=" + email + ", movil=" + movil
				+ ", sexo=" + sexo + ", create_time=" + create_time + ", update_time=" + update_time
				+ ", facturas=" + facturas + "]";
	}
	

	// GETTERS AND SETTERS
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

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public List<Facturas> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Facturas> facturas) {
		this.facturas = facturas;
	}
	
	

}
