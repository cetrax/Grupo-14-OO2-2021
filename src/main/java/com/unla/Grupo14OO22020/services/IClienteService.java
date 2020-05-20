package com.unla.Grupo14OO22020.services;

import java.util.List;
import com.unla.Grupo14OO22020.entities.Cliente;
import com.unla.Grupo14OO22020.models.ClienteModel;

public interface IClienteService {
	
	public abstract List<Cliente> getAll();

	public ClienteModel insertOrUpdate(ClienteModel clienteModel);

	public ClienteModel findByIdPersona(int id);

	public boolean remove(int id);

}
