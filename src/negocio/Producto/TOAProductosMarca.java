package negocio.Producto;

import java.util.Collection;


import negocio.Marca.TMarca;

public class TOAProductosMarca {
	
	private Collection<TProducto> productos;
	private TMarca marca;
	
	public TOAProductosMarca(Collection<TProducto> productos, TMarca marca) {
		this.productos = productos;
		this.marca = marca;
	}
	
	public TMarca getMarca() {
		return marca;
	}
	
	public void setMarca(TMarca marca) {
		this.marca = marca;
	}
	
	public Collection<TProducto> getProductos() {
		return productos;
	}
	
	public void setProductos(Collection<TProducto> productos) {
		this.productos = productos;
	}
}
