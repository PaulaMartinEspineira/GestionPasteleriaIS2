package integracion.Factoria;

import integracion.Marca.DAOMarca;
import integracion.Marca.DAOMarcaProveedor;
import integracion.Producto.DAOProducto;
import integracion.Proveedor.DAOProveedor;
import integracion.Cliente.DAOCliente;
import integracion.Facturas.DAOFactura;
import integracion.Facturas.DAOLineaFactura;
import integracion.Empleado.DAOEmpleado;

public abstract class FactoriaAbstractaIntegracion {

	private static FactoriaAbstractaIntegracion instancia = null;

	public static FactoriaAbstractaIntegracion getInstance() { // creacion perezosa
		if (instancia == null) {
			instancia = new FactoriaIntegracion();
		}
		return instancia;
	}

	// metodos para crear DAOSS

	public abstract DAOFactura crearDAOFactura();
	public abstract DAOLineaFactura crearDAOLineaFactura();
	public abstract DAOMarca crearDAOMarca();
	public abstract DAOMarcaProveedor crearDAOMarcaProveedor();
	public abstract DAOProducto crearDAOProducto();
	public abstract DAOCliente crearDAOCliente();
	public abstract DAOEmpleado crearDAOEmpleado();
	public abstract DAOProveedor crearDAOProveedor();


}
