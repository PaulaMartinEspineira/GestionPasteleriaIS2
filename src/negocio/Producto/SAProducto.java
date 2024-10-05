package negocio.Producto;

import java.util.Collection;

public interface SAProducto {
	public int altaProducto(TProducto producto);
	public int bajaProducto(String nombre);
	public TProducto buscarProducto(String nombre);
	public int actualizarProducto(TProducto producto);
	public Collection<TProducto> listarProductos();
	public Collection<TProducto> listarProductosPorMarca(String nombreMarca);
	public TOAProductosMarca listarProductosConMarca(String nombreMarca);
}
