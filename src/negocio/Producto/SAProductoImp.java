package negocio.Producto;

import java.util.Collection;
import java.util.ArrayList;

import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Producto.DAOProducto;
import negocio.Marca.TMarca;
import integracion.Marca.DAOMarca;


public class SAProductoImp implements SAProducto {

	@Override
	public int altaProducto(TProducto producto) throws IllegalArgumentException {
		int id = -1;
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		TProducto prod = daoProducto.buscarProducto(producto.getNombre());
		TMarca marca = daoMarca.buscarMarca(producto.getMarca());
		if (marca == null) {
			throw new IllegalArgumentException("Marca no existente.");
		}
		else if (!marca.getActivo()) {
			throw new IllegalArgumentException("Marca no activa.");
		}
		if(prod == null) {
			id = daoProducto.altaProducto(producto);
		}
		else if (!prod.getActivo()) {
			producto.setId(prod.getId());
			producto.setActivo(true);
			id = daoProducto.actualizarProducto(producto);
		}
		else {
			throw new IllegalArgumentException("Producto ya existe.");
		}
		return id;
	}

	@Override
	public int bajaProducto(String nombre) throws IllegalArgumentException {
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		TProducto prod = daoProducto.buscarProducto(nombre);
		if (prod == null) {
			throw new IllegalArgumentException("Producto no existente.");
		}else if(!prod.getActivo()) {
			throw new IllegalArgumentException("El producto ya ha sido dado de baja.");
		}
		daoProducto.bajaProducto(prod.getId());
		return prod.getId();
	}

	@Override
	public TProducto buscarProducto(String nombre) {
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		return daoProducto.buscarProducto(nombre);
	}

	@Override
	public int actualizarProducto(TProducto producto) throws IllegalArgumentException {
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		TMarca marca = daoMarca.buscarMarca(producto.getMarca());
		TProducto prod = daoProducto.buscarProducto(producto.getNombre());
		if (marca == null) {
			throw new IllegalArgumentException("Marca no existente.");
		}
		else if (!marca.getActivo()) {
			throw new IllegalArgumentException("Marca no activa.");
		}
		if(prod == null) {
			throw new IllegalArgumentException("Producto no existente.");
		}
		producto.setId(prod.getId());
		return daoProducto.actualizarProducto(producto);
	}

	@Override
	public Collection<TProducto> listarProductos() {
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		return daoProducto.listarProductos();
	}
	
	@Override
	public Collection<TProducto> listarProductosPorMarca(String nombreMarca) {
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		TMarca marca  = daoMarca.buscarMarca(nombreMarca);
		if (marca == null) {
			throw new IllegalArgumentException("Marca no existente");
		}
		else {
			int marcaId = marca.getID();
			DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
			Collection<TProducto> productos = daoProducto.listarProductos();
			ArrayList<TProducto> productosPorMarca = new ArrayList<TProducto>();
			for (TProducto producto : productos) {
				if (producto.getMarca() == marcaId) {
					productosPorMarca.add(producto);
				}
			}
			return productosPorMarca;
		}
	}
	
	public TOAProductosMarca listarProductosConMarca(String nombreMarca) {
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		TMarca marca  = daoMarca.buscarMarca(nombreMarca);
		if (marca == null) {
			throw new IllegalArgumentException("Marca no existente");
		}
		else {
			int marcaId = marca.getID();
			DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
			Collection<TProducto> productos = daoProducto.listarProductos();
			ArrayList<TProducto> productosPorMarca = new ArrayList<TProducto>();
			for (TProducto producto : productos) {
				if (producto.getMarca() == marcaId) {
					productosPorMarca.add(producto);
				}
			}
			TOAProductosMarca productosMarca = new TOAProductosMarca(productos, marca);
			return productosMarca;
		}
	}

}
