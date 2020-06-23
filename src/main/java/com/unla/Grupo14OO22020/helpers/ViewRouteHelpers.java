package com.unla.Grupo14OO22020.helpers;

public class ViewRouteHelpers {
	public final static String HOME = "home/home";
	public final static String ROUTE = "/home";
	public final static String INDEX = "home/index";
	public final static String HOME_EMPLEADO = "home/homeEmpleado";
	public final static String ROUTE_EMPLEADO = "/homeEmpleado";
	//redirects	
	public final static String ROUTE_INDEX = "/index";
	public final static String CLIENT_ROOT = "/clientes";
	public final static String EMPLEADO_ROOT="/empleados";
	public final static String PRODUCTO_ROOT="/productos";
	public final static String LOCAL_ROOT="/locales";
	public final static String PEDIDO_ROOT="/pedidos";
	public static final String PEDIDO_LOCAL_EMPLEADO_ROOT = "/pedidos/pedEmpleado";
	public final static String LOTE_ROOT="/lotes";
	

	//Cliente
	public final static String CLIENT_UPDATE = "cliente/update";
	public final static String CLIENT_ADD = "cliente/new";
	public final static String CLIENT_INDEX = "cliente/index";
	
	//Empleado
	public final static String EMPLEADO_INDEX = "empleado/index";
	public final static String EMPLEADO_ADD = "empleado/new";
	public final static String EMPLEADO_UPDATE = "empleado/update";

	//Producto
	public final static String PRODUCTO_INDEX = "producto/index";
	public final static String PRODUCTO_ADD = "producto/new";
	public final static String PRODUCTO_UPDATE = "producto/update";

	//Local	
	public final static String LOCAL_INDEX = "local/index";
	public final static String LOCAL_ADD = "local/new";
	public final static String LOCAL_UPDATE = "local/update";
	public final static String LOCAL_CALCULAR = "local/distancia2";
	public final static String LOCAL_MOSTRAR = "local/distancia";
	public final static String LOCAL_CALCULAR_DIS = "local/distancia3";
	
	//Pedido
	public final static String PEDIDO_INDEX = "pedido/index";
	public final static String PEDIDO_ADD = "pedido/new";
	public final static String PEDIDO_UPDATE = "pedido/update";
	public static final String PEDIDO_PEDIR_ID_EMPLEADO = "pedido/empDelPed";
	public static final String MOSTRAR_PEDIDOS_LOCAL_EMPLEADO = "pedido/pedEmpleado";//pedidos relacionados con el empleado
	public final static String ADD_PEDIDO_DEL_EMPLEADO = "pedido/newPedidoEmpleado";
	public final static String PEDIDO_UPDATE_EMPL_ORIG = "pedido/updateEmpleadoOriginal";
	public final static String PEDIDO_UPDATE_EMPL_AUXI = "pedido/updateEmpleadoAuxilar";
	
	//lote
	public final static String LOTE_UPDATE = "lote/update";
	public final static String LOTE_ADD = "lote/new";
	public final static String LOTE_INDEX = "lote/index";
		

}//Fin class
