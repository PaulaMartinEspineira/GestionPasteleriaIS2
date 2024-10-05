package negocio.Cliente;

import java.util.Collection;

public interface SACliente {
	public int altaCliente(TCliente cl);

	public int bajaCliente(String dni);

	public int actualizarCliente(TCliente cl);

	public TCliente buscarCliente(String dni);

	public Collection<TCliente> listarClientes();
}
