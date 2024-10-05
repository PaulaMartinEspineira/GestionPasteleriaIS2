package integracion.Marca;

import java.util.Collection;

import negocio.Marca.TMarca;

public interface DAOMarca {
	public int altaMarca(TMarca marca);
	public boolean bajaMarca(int id);
	public int actualizarMarca(TMarca marca);
	public TMarca buscarMarca(int id); 
	public TMarca buscarMarca(String nombre); 
	public Collection<TMarca> listarMarcas();
}
