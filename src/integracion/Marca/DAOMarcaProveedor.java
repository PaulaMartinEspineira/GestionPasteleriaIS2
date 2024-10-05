package integracion.Marca;

import java.util.Collection;

import negocio.Marca.TMarcaProveedor;

public interface DAOMarcaProveedor {

	public int altaMarcaProveedor(TMarcaProveedor marcaProveedor);
	public boolean bajaMarcaProveedor(String nombreMarca, String nombreProv);
	public TMarcaProveedor buscarMarcaProveedor(String nombreMarca, String nombreProv);
	public Collection<TMarcaProveedor> listarMarcaProveedor();
}
