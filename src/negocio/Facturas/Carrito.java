package negocio.Facturas;

import java.util.ArrayList;

public class Carrito {
	private ArrayList<TLineaFactura> lista_productos;

	public Carrito() {
		this.lista_productos = new ArrayList<TLineaFactura>();
	}

	public ArrayList<TLineaFactura> getProductos() {
		return lista_productos;
	}

	public void anadirProducto(TLineaFactura linea) {
		int i = buscarEnLista(linea);
		if (i == -1) {
			// si el producto no existe creamos una nueva linea
			this.lista_productos.add(linea);
		} else {
			// si ya existe una linea actualizamos su cantidad
			this.lista_productos.get(i)
					.setCantidadProducto(this.lista_productos.get(i).getCantidad() + linea.getCantidad());
		}
	}

	public boolean eliminarProducto(TLineaFactura linea) {
		int i = buscarEnLista(linea);
		if (i == -1) {
			// no se puede eliminar un producto que no existe
			return false;
		} else {
			this.lista_productos.get(i)
					.setCantidadProducto(this.lista_productos.get(i).getCantidad() - linea.getCantidad());
			// si hemos quitado mas productos de los que hay borramos la linea
			if (this.lista_productos.get(i).getCantidad() <= 0) {
				// que como no ha sido creada podemos borrarla de verdad)
				this.lista_productos.remove(i);
			}
			return true;
		}
	}

	// busca si ya existe el producto en el carrito
	private int buscarEnLista(TLineaFactura linea) {
		// devuelve la posicion de la linea que lo trata o -1 si no existe
		boolean encontrado = false;
		int i = 0;
		while (i < this.lista_productos.size() && !encontrado) {
			if (linea.getIdProducto() == this.lista_productos.get(i).getIdProducto()) {
				encontrado = true;
			}
			i++;
		}
		if (encontrado)
			return i - 1;
		else
			return -1;
	}
}
