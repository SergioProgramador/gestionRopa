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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "facturas")
public class Facturas implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "fecha_vencimiento")
	private Date fecha_vencimiento; 
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "iva", columnDefinition = "int default 21")
	private int IVA;
	
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_time;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_time;
	
	//RELACION CON CLIENTES
	@JsonIgnoreProperties(value={"facturas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY)
	private Clientes cliente_id;
	
	//RELACION CON LINEAFACTURA
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="factura_id")
	private List<LineaFactura> listLineaFacturas;
	
	@PrePersist
	public void prePersist(){
		this.create_time = new Date();
	}
	
	//METODO PARA EL SUBTOTAL
	public float getSubTotal() {
		float subtotal = 0;
		for(LineaFactura linea: listLineaFacturas) {
			subtotal += linea.getImporte();
		}
		return subtotal;
	}
	
	//METODO PARA EL TOTAL
	public float getTotal() {
		float cant_iva = (float) (this.getSubTotal()*this.getIVA())/100;
		float total = (float) this.getSubTotal()+cant_iva;
		return total;	
	}

	//TOSTRING
	@Override
	public String toString() {
		return "Facturas [id=" + id + ", fecha_vencimiento=" + fecha_vencimiento + ", titulo=" + titulo
				+ ", descripcion=" + descripcion + ", IVA=" + IVA +", create_time=" + create_time + ", update_time=" + update_time + ", cliente_id=" + cliente_id
				+ ", listLineaFacturas=" + listLineaFacturas + "]";
	}
	
	//CONSTRUCTORS
	public Facturas() {
		listLineaFacturas = new ArrayList<>();
	}

	public Facturas(int id, Date fecha_vencimiento, String titulo, String descripcion, int iVA, Date create_time, Date update_time, Clientes cliente_id, List<LineaFactura> listLineaFacturas) {
		super();
		this.id = id;
		this.fecha_vencimiento = fecha_vencimiento;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.IVA = iVA;
		this.create_time = create_time;
		this.update_time = update_time;
		this.cliente_id = cliente_id;
		this.listLineaFacturas = listLineaFacturas;
	}

	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}


	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getIVA() {
		return IVA;
	}


	public void setIVA(int iVA) {
		IVA = iVA;
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

	public Clientes getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Clientes cliente_id) {
		this.cliente_id = cliente_id;
	}

	public List<LineaFactura> getListLineaFacturas() {
		return listLineaFacturas;
	}


	public void setListLineaFacturas(List<LineaFactura> listLineaFacturas) {
		this.listLineaFacturas = listLineaFacturas;
	}
	
	
	
}
