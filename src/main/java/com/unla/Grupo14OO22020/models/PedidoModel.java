package com.unla.Grupo14OO22020.models;

import java.time.LocalDate;

import org.springframework.lang.Nullable;

public class PedidoModel {

	private int idPedido;
	private LocalDate fecha;
	private ProductoModel producto;
	private int cantidad;
	private ClienteModel cliente;
	private EmpleadoModel vendedorOriginal;
	@Nullable
	private EmpleadoModel vendedorAuxiliar;
	@Nullable
	private LocalModel local;
	@Nullable
	private float subtotal;
	@Nullable
	private boolean aceptado;
	
	
	public PedidoModel() { }
	
	public PedidoModel(int idPedido, LocalDate fecha, ProductoModel producto, int cantidad, ClienteModel cliente,
			EmpleadoModel vendedorOriginal, EmpleadoModel vendedorAuxiliar, LocalModel local,
			boolean aceptado) {
		this.idPedido = idPedido;
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.cliente = cliente;
		this.vendedorOriginal = vendedorOriginal;
		this.vendedorAuxiliar = vendedorAuxiliar;
		this.local = local;
		this.subtotal = CalcularSubtotal();;
		this.aceptado = aceptado;
	}
	
	
	public PedidoModel(LocalModel local) {
		this.local = local;
	}

	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProducto(ProductoModel producto) {
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


	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public EmpleadoModel getVendedorOriginal() {
		return vendedorOriginal;
	}

	public void setVendedorOriginal(EmpleadoModel vendedorOriginal) {
		this.vendedorOriginal = vendedorOriginal;
	}

	public EmpleadoModel getVendedorAuxiliar() {
		return vendedorAuxiliar;
	}

	public void setVendedorAuxiliar(EmpleadoModel vendedorAuxiliar) {
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


	public float CalcularSubtotal() {
		return producto.getPrecio()*cantidad;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	
}//fin class