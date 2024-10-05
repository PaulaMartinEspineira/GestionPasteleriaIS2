package negocio.Proveedor;

import java.util.Collection;

import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Proveedor.DAOProveedor;

public class SAProveedorImp implements SAProveedor{

	@Override
	public int altaProveedor(TProveedor proveedor) throws IllegalArgumentException {
		DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor();
		int id = -1;
		
		TProveedor prov = daoProveedor.buscarProveedor(proveedor.getNombre());
		if (prov == null) { //no existía previamente
			id = daoProveedor.altaProveedor(proveedor);	
		}
		else if (!prov.getActivo()) { //sí existía pero no activo
			proveedor.setID(prov.getID());
			proveedor.setActivo(true);
			id = daoProveedor.actualizarProveedor(proveedor);
		}
		else throw new IllegalArgumentException("Proveedor ya existe.");
		return id;
	}

	@Override
	public int bajaProveedor(String nombre) {
		DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor();
		TProveedor proveedor = daoProveedor.buscarProveedor(nombre);
		
		//no se puede realizar la acción si el proveedor no existe o no está activo
		if (proveedor == null ) {
			throw new IllegalArgumentException("Proveedor no existente.");
		}
		else if (!proveedor.getActivo()) {
			throw new IllegalArgumentException("El proveedor ya ha sido dada de baja.");
		}
		
		daoProveedor.bajaProveedor(proveedor.getID());
		return proveedor.getID();
	}

	@Override
	public int actualizarProveedor(TProveedor proveedor) {
		DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor();
		TProveedor prov = daoProveedor.buscarProveedor(proveedor.getNombre());
		
		if(prov == null) {
			throw new IllegalArgumentException("Proveedor no existente");
		}
		proveedor.setID(prov.getID());
		return daoProveedor.actualizarProveedor(proveedor);
		
	}

	@Override
	public TProveedor buscarProveedor(String nombre) {
		DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor();
		return daoProveedor.buscarProveedor(nombre);
	}

	@Override
	public Collection<TProveedor> listarProveedores() {
		DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor();
		return daoProveedor.listarProveedores();
	}

}
