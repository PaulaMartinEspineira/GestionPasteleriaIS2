package integracion.Factoria;

import integracion.Marca.DAOMarca;

import integracion.Marca.DAOMarcaImp;
import integracion.Marca.DAOMarcaProveedor;
import integracion.Marca.DAOMarcaProveedorImp;
import integracion.Producto.DAOProducto;
import integracion.Producto.DAOProductoImp;
import integracion.Proveedor.DAOProveedor;
import integracion.Proveedor.DAOProveedorImp;
import integracion.Cliente.DAOCliente;
import integracion.Cliente.DAOClienteImp;
import integracion.Empleado.DAOEmpleado;
import integracion.Empleado.DAOEmpleadoImp;
import integracion.Facturas.DAOFactura;
import integracion.Facturas.DAOFacturaImp;
import integracion.Facturas.DAOLineaFactura;
import integracion.Facturas.DAOLineaFacturaImp;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion {

	@Override
	public DAOFactura crearDAOFactura() {
		return new DAOFacturaImp();
	}
	
	@Override
	public DAOLineaFactura crearDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}
	
	@Override
	public DAOMarca crearDAOMarca() {
		return new DAOMarcaImp();
	}

	@Override
	public DAOMarcaProveedor crearDAOMarcaProveedor() {
		return new DAOMarcaProveedorImp();
	}

	@Override
	public DAOProducto crearDAOProducto() {
		return new DAOProductoImp();
	}

	@Override
	public DAOCliente crearDAOCliente() {
		return new DAOClienteImp();
	}

	@Override
	public DAOEmpleado crearDAOEmpleado() {
		return new DAOEmpleadoImp();
	}

	@Override
	public DAOProveedor crearDAOProveedor() {
		return new DAOProveedorImp();
	}
	
	

}