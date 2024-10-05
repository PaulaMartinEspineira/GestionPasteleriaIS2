package negocio.Cliente;

import java.util.Collection;

import integracion.Cliente.DAOCliente;
import integracion.Factoria.FactoriaAbstractaIntegracion;

public class SAClienteImp implements SACliente {

	@Override
	public int altaCliente(TCliente cl) throws IllegalArgumentException{
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		TCliente tcliente = daoCliente.buscarCliente(cl.getDNI());
		int id = -1;
		
		if (tcliente == null) { // El cliente no está en nuestro fichero JSON.
			id = daoCliente.altaCliente(cl);
		}
		else if (!tcliente.getActivo()) { // El cliente está en nuestro fichero JSON pero se encuentra dado de baja.
			cl.setId(tcliente.getId());
			cl.setActivo(true);
			id = daoCliente.actualizarCliente(cl);
		}
		else {
			throw new IllegalArgumentException("El cliente ya existe.");
		}
		return id;
	}

	@Override
	public int bajaCliente(String dni) throws IllegalArgumentException{
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		TCliente tcliente = daoCliente.buscarCliente(dni);
		if (tcliente == null) { // El cliente no está en nuestro fichero JSON.
			throw new IllegalArgumentException("El cliente no existe.");
		}
		else if(!tcliente.getActivo()) { // El cliente está en nuestro fichero JSON pero ya ha sido dado de baja.
			throw new IllegalArgumentException("El cliente ya ha sido dado de baja.");
		}
		daoCliente.bajaCliente(tcliente.getId());
		return tcliente.getId();
	}

	@Override
	public int actualizarCliente(TCliente cl) {
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		TCliente tcliente = daoCliente.buscarCliente(cl.getDNI());
		if (tcliente == null) { // El cliente no está en nuestro fichero JSON.
			throw new IllegalArgumentException("El cliente no existe.");
		}
		cl.setId(tcliente.getId());
		return daoCliente.actualizarCliente(cl);
	}

	@Override
	public TCliente buscarCliente(String dni) {
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		return daoCliente.buscarCliente(dni);
	}

	@Override
	public Collection<TCliente> listarClientes() {
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		return daoCliente.listarClientes();
	}
	
}
