package integracion.Cliente;

import java.util.Collection;


import negocio.Cliente.TCliente;

public interface DAOCliente {

	public int altaCliente(TCliente cliente);

	public void bajaCliente(int id);

	public TCliente buscarCliente(int id);
	
	public TCliente buscarCliente(String dni);

	public int actualizarCliente(TCliente cliente);

	public Collection<TCliente> listarClientes();
}