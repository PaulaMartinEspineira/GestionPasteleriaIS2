package negocio.Factoria;

import negocio.Cliente.SACliente;
import negocio.Cliente.SAClienteImp;
import negocio.Facturas.SAFactura;
import negocio.Facturas.SAFacturaImp;
import negocio.Marca.SAMarca;
import negocio.Marca.SAMarcaImp;
import negocio.Producto.SAProducto;
import negocio.Producto.SAProductoImp;
import negocio.Proveedor.SAProveedor;
import negocio.Proveedor.SAProveedorImp;
import negocio.Empleados.SAEmpleado;
import negocio.Empleados.SAEmpleadoImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio {

	@Override
	public SAFactura crearSAFactura() {
		return new SAFacturaImp();
	}
	
	@Override
	public SAMarca crearSAMarca() {
		return new SAMarcaImp();
	}
	
	@Override
	public SAProducto creaSAProducto() {
		return new SAProductoImp();
	}

	@Override
	public SACliente crearSACliente() {
		return new SAClienteImp();
	}
	
	@Override
	public SAEmpleado crearSAEmpleado() {
		return new SAEmpleadoImp();
	}

	@Override
	public SAProveedor crearSAProveedor() {
		return new SAProveedorImp();
	}
	
	

}
