package negocio.Factoria;

import negocio.Cliente.SACliente;
import negocio.Empleados.SAEmpleado;
import negocio.Facturas.SAFactura;
import negocio.Marca.SAMarca;
import negocio.Producto.SAProducto;
import negocio.Proveedor.SAProveedor;

public abstract class FactoriaAbstractaNegocio {

private static FactoriaAbstractaNegocio instance = null;
	
	public static FactoriaAbstractaNegocio getInstance() {
		if(instance == null) {
			instance = new FactoriaNegocio();
		}
		return instance;
	}
	
	public abstract SAFactura crearSAFactura();
	public abstract SAMarca crearSAMarca();
	public abstract SAProducto creaSAProducto();
	public abstract SACliente crearSACliente();
	public abstract SAEmpleado crearSAEmpleado();
	public abstract SAProveedor crearSAProveedor();

}
