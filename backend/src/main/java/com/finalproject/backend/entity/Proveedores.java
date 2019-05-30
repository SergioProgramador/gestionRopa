package com.finalproject.backend.entity;

import java.io.Serializable;
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
@Table(name="proveedores")
public class Proveedores implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="empresa")
	private String empresa;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="email")
	private String email;
	
	@Column(name="frecuencia")
	private String frecuencia;
	
	@Column(name="estado_pago")
	private String estado_pago;
	
	@Column(name="cantidad")
	private float cantidad;
	
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_time;
	
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_time;
	
	//RELACION CON PRODUCTOS
	@JsonIgnoreProperties({"proveedores" , "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch=FetchType.LAZY, mappedBy="proveedores", cascade=CascadeType.ALL)
	private List<Productos> productos;
	
	//METODO TO STRING
	@Override
	public String toString() {
		return "Proveedores [id=" + id + ", empresa=" + empresa + ", descripcion=" + descripcion + ", direccion="
				+ direccion + ", telefono=" + telefono + ", email=" + email + ", frecuencia=" + frecuencia
				+ ", estado_pago=" + estado_pago + ", cantidad=" + cantidad + ", create_time=" + create_time
				+ ", update_time=" + update_time + ", productos=" + productos + "]";
	}
	
	//CONSTRUCTOR
	public Proveedores(){
		
	}

	public Proveedores(int id, String empresa, String descripcion, String direccion, String telefono, String email,
			String frecuencia, String estado_pago, float cantidad, Date create_time, Date update_time,
			List<Productos> productos) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.frecuencia = frecuencia;
		this.estado_pago = estado_pago;
		this.cantidad = cantidad;
		this.create_time = create_time;
		this.update_time = update_time;
		this.productos = productos;
	}

	@PrePersist
	public void prePersist(){
		this.create_time = new Date();
	}
	
	//GETTERS AND SETTERS	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getEstado_pago() {
		return estado_pago;
	}

	public void setEstado_pago(String estado_pago) {
		this.estado_pago = estado_pago;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
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
	
	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}
	

	
	
	

}
