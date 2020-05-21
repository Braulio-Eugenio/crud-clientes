package br.com.zup.pgg.service;

import java.util.List;

import br.com.zup.pgg.dao.ClienteDao;
import br.com.zup.pgg.model.Cliente;

public class ClienteService {
	
	ClienteDao clienteDao = new ClienteDao();
	
	
	public void insereCliente(Cliente cliente)  {
		
		clienteDao.insereCliente(cliente);
		
	}
	
	public List<Cliente> buscaListaClientes() {
		
		
		return clienteDao.listaClientes();
	}
	public void deleteCliente(String cpf) {
		clienteDao.deletaCliente(cpf);
	}
}
