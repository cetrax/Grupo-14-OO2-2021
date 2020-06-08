package com.unla.Grupo14OO22020.models;

import org.springframework.lang.Nullable;

public class PedidoModel {

	private int idPedido;
	private ProductoModel producto;
	private int cantidad;
	private ClienteModel cliente;
	private EmpleadoModel vendedorOriginal;
	@Nullable
	private EmpleadoModel vendedorAuxiliar;
	private float subtotal;
	private boolean aceptado;
	

	public PedidoModel(int idPedido, ProductoModel producto, int cantidad, ClienteModel cliente2,
			EmpleadoModel empleado, EmpleadoModel empleado2, boolean aceptado) {
		super();
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.cliente = cliente2;
		this.vendedorOriginal = empleado;
		this.vendedorAuxiliar = empleado2;
		this.aceptado = aceptado;
		this.subtotal = CalcularSubtotal();
	}

	public PedidoModel() {
	
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
	
	

}