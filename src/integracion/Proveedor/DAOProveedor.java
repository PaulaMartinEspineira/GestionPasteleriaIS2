package integracion.Proveedor;

import java.util.Collection;

import negocio.Proveedor.TProveedor;

public interface DAOProveedor {

		public int altaProveedor(TProveedor proveedor);
		public void bajaProveedor(int id);
		public int actualizarProveedor(TProveedor proveedor);
		public TProveedor buscarProveedor(int id); 
		public TProveedor buscarProveedor(String nombre); 
		public Collection<TProveedor> listarProveedores();


}
