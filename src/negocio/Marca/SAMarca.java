package negocio.Marca;

import java.util.Collection;

public interface SAMarca {
	public int altaMarca(TMarca marca);
	public int bajaMarca(String nombre);
	public int actualizarMarca(TMarca marca);
	public TMarca buscarMarca(String nombre);
	public Collection<TMarca> listarMarcas();
}
