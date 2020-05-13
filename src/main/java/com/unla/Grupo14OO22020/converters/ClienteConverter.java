package com.unla.Grupo14OO22020.converters;


import org.springframework.stereotype.Component;

import com.unla.Grupo14OO22020.entities.Cliente;
import com.unla.Grupo14OO22020.models.ClienteModel;




@Component("clienteConverter")
public class ClienteConverter {
	
	public ClienteModel entityToModel(Cliente cliente) {
		return new ClienteModel(cliente.getIdPersona(),cliente.getDni(),cliente.getNombre(),cliente.getApellido(),cliente.getFechaDeNacimiento(),cliente.getEmail());
	}
	
	public Cliente modelToEntity(ClienteModel clienteModel) {
		return new Cliente(clienteModel.getIdPersona(),clienteModel.getDni(),clienteModel.getNombre(),clienteModel.getApellido(),clienteModel.getFechaDeNacimiento(),clienteModel.getEmail());
	}

}
