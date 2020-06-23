package com.unla.Grupo14OO22020.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;


@Entity
@Table(name="Pedido")
public class Pedido {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	
	@Column(name = "fecha")
	@CreationTimestamp
	private LocalDate fecha;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Producto producto;
	
	@Column(name = "cantidad")
	private int cantidad;
	

	@OneToOne(cascade = CascadeType.MERGE)
	private Cliente cliente;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Empleado vendedorOriginal;
	
	@Nullable
	@OneToOne(fetch=FetchType.LAZY)
	private Empleado vendedorAuxiliar;
	
	@Nullable
	@OneToOne(fetch=FetchType.LAZY)
	private Local local;
	
	@Nullable
	@Column(name = "subtotal")
	private float subtotal;
	
	@Nullable	
	@Column(name = "aceptado")
	private boolean aceptado;

	
	
	public Pedido() { }
	
	
	public Pedido(int idPedido, LocalDate fecha, Producto producto, int cantidad, Cliente cliente,
			Empleado vendedorOriginal, Empleado vendedorAuxiliar, Local local, boolean aceptado) {
		super();
		this.idPedido = idPedido;
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.cliente = cliente;
		this.vendedorOriginal = vendedorOriginal;
		this.vendedorAuxiliar = vendedorAuxiliar;
		this.local = local;
		this.subtotal = CalcularSubtotal();
		this.aceptado = aceptado;
	}


	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getVendedorOriginal() {
		return vendedorOriginal;
	}

	public void setVendedorOriginal(Empleado vendedorOriginal) {
		this.vendedorOriginal = vendedorOriginal;
	}

	public Empleado getVendedorAuxiliar() {
		return vendedorAuxiliar;
	}

	public void setVendedorAuxiliar(Empleado vendedorAuxiliar) {
		this.vendedorAuxiliar = vendedorAuxiliar;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public float getTotal() {
		return subtotal;
	}

	public void setTotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
    public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	/*  @Override
	public String toString() {
		return "Pedido [producto=" + producto + ", cantidad=" + cantidad + ", local=" + local + ", cliente=" + cliente
				+ ", vendedorOriginal=" + vendedorOriginal + ", vendedorAuxiliar=" + vendedorAuxiliar + "]\n\n";
	}
	*/
	public float CalcularSubtotal() {
		return producto.getPrecio()*cantidad;
	}

}
