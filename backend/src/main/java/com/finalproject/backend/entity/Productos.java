package com.finalproject.backend.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "productos")
public class Productos implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "talla")
	private String talla;
	
	@Column(name = "material")
	private String material;
	
	@Column(name = "precio")
	private float precio;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_time;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_time;
	
	@PrePersist
	public void prePersist(){
		this.create_time = new Date();
	}
	
	//RELACION CON CATEGORIAS
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name="productos_categorias",
			joinColumns=@JoinColumn(name="idproducto"),
			inverseJoinColumns=@JoinColumn(name="idcategoria")
		)
	private List<Categorias> categorias;
	
	//RELACION CON PROVEEDORES
	@JsonIgnoreProperties({"productos" , "hibernateLazyInitializer", "handler"})
	@ManyToOne
    @JoinColumn(name = "proveedores_id")
    private Proveedores proveedores;
	
	//CONSTRUCTOR
	public Productos(int id, String nombre, String descripcion, String talla, String material, float precio,
			String color, int stock, String imagen, Date create_time, Date update_time, List<Categorias> categorias,
			Proveedores proveedores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.talla = talla;
		this.material = material;
		this.precio = precio;
		this.color = color;
		this.stock = stock;
		this.imagen = imagen;
		this.create_time = create_time;
		this.update_time = update_time;
		this.categorias = categorias;
		this.proveedores = proveedores;
	}
	
	public Productos() {
		
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	
	public List<Categorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categorias> categorias) {
		this.categorias = categorias;
	}

	public Proveedores getProveedores() {
		return proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}


	
	
	

}
