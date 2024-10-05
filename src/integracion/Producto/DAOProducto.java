package integracion.Producto;

import java.util.Collection;
import negocio.Producto.TProducto;

public interface DAOProducto {
	public int altaProducto(TProducto producto);
	public void bajaProducto(int id);
	public TProducto buscarProducto(String nombre);
	public TProducto buscarProducto(int id);
	public int actualizarProducto(TProducto producto);
	public Collection<TProducto> listarProductos();
}