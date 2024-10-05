package negocio.Proveedor;

import java.util.Collection;

public interface SAProveedor {
	public int altaProveedor(TProveedor proveedor);
	public int bajaProveedor(String nombre);
	public int actualizarProveedor(TProveedor proveedor);
	public TProveedor buscarProveedor(String nombre);
	public Collection<TProveedor> listarProveedores();
}
