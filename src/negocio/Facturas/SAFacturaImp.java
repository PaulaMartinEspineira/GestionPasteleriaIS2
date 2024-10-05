package negocio.Facturas;

import java.util.Collection;

import integracion.Cliente.DAOCliente;
import integracion.Empleado.DAOEmpleado;
import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Facturas.*;
import integracion.Producto.DAOProducto;
import negocio.Cliente.TCliente;
import negocio.Empleados.TEmpleado;
import negocio.Producto.TProducto;

import java.util.ArrayList;

public class SAFacturaImp implements SAFactura {

	public TFacturaLineaFacturas buscarFactura(int id) {
		TFacturaLineaFacturas factura_lineasFactura = null;
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
		TFactura factura = daoFactura.buscarFactura(id);
		if (factura != null) {
			DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
			ArrayList<TLineaFactura> lineas_factura_totales = (ArrayList<TLineaFactura>) daoLineaFactura
					.mostrarLineasFactura();
			ArrayList<TLineaFactura> lineas_factura_individuales = new ArrayList<>();
			// seleccionamos las que pertenecen a la factura actual
			if (lineas_factura_totales != null) {
				for (int j = 0; j < lineas_factura_totales.size(); ++j) {
					if (lineas_factura_totales.get(j).getIdFactura() == id) {
						lineas_factura_individuales.add(lineas_factura_totales.get(j));
					}
				}
			}
			// creamos el resto de elementos necesarios para crear la factura
			factura_lineasFactura = new TFacturaLineaFacturas(factura, lineas_factura_individuales);
		}
		return factura_lineasFactura;
	}

	public Collection<TFactura> listarFacturas() {
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
		return daoFactura.listarFacturas();
	}

	private int crearFactura(TDatosVenta datos) {
		// creamos los dao
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
		DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();

		// Creamos variables auxiliares
		ArrayList<TLineaFactura> lineas_factura_definitivas = new ArrayList<TLineaFactura>();
		double precio_total = 0;
		int id_factura = -1;

		// buscamos el cliente y el vendedor
		TCliente cliente = daoCliente.buscarCliente(datos.getIdCliente());
		TEmpleado vendedor = daoEmpleado.buscarEmpleado(datos.getIdVendedor());

		// solo si existen y estan activos podemos crear la factura
		if (cliente != null && cliente.getActivo() && vendedor != null && vendedor.getActivo()) {
			int i = 0;
			// Recorremos las lineas de factura de nuestra nueva factura
			ArrayList<TLineaFactura> lineas_factura_por_comprobar = datos.getCarrito();
			while (i < lineas_factura_por_comprobar.size()) {
				TLineaFactura linea_factura = lineas_factura_por_comprobar.get(i);
				// comprobamos que es una linea válida
				TProducto producto = daoProducto.buscarProducto(linea_factura.getIdProducto());
				if (producto == null || linea_factura.getCantidad() < 0 || producto.getStock() <= 0
						|| producto.getPrecio() < 0) {
					;
				} else {
					if (producto.getStock() <= linea_factura.getCantidad()) {
						linea_factura.setCantidadProducto(producto.getStock());
					}
					// actualizamos los precios
					double precio_linea = producto.getPrecio() * linea_factura.getCantidad();
					linea_factura.setPrecio(precio_linea);
					precio_total += linea_factura.getPrecio();
					// Actualizamos la informacion del producto
					producto.setStock(producto.getStock() - linea_factura.getCantidad());
					daoProducto.actualizarProducto(producto);
					// guardamos la linea de factura
					lineas_factura_definitivas.add(linea_factura);
				}
				i++;
			}

			if (lineas_factura_definitivas.size() > 0) {// si hay lineas válidas y no hay problemas en la factura
				// creamos la factura
				id_factura = daoFactura.crearFactura(
						new TFactura(0, cliente.getId(), vendedor.getId(), precio_total, datos.getFecha(), true));
				// Creamos las lineas de factura
				for (TLineaFactura lf : lineas_factura_definitivas) {
					lf.setIdFactura(id_factura);
					daoLineaFactura.crearLineaFactura(lf);
				}
				// Actualizamos la informacion del vendedor
				vendedor.setNumVentas(vendedor.getNumVentas() + 1);
				daoEmpleado.actualizarEmpleado(vendedor);
			}
		}

		return id_factura;
	}

	public int cerrarVenta(TDatosVenta datos) {
		return crearFactura(datos);
	}

	public boolean modificarFactura(int id_f, int id_c, int id_v, String fecha) {
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();
		// buscamos el cliente y el vendedor
		TCliente cliente = daoCliente.buscarCliente(id_c);
		TEmpleado vendedor = daoEmpleado.buscarEmpleado(id_v);

		// solo si existen y estan activos podemos modificar la factura
		if (cliente != null && cliente.getActivo() && vendedor != null && vendedor.getActivo()) {
			DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
			return daoFactura.modificarFactura(id_f, id_c, id_v, fecha);
		}
		return false;
	}

//EXTRAS
	public Collection<TFactura> listarFacturasPorCliente(int id_cliente) {
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		TCliente cliente = daoCliente.buscarCliente(id_cliente);
		if (cliente != null) {// si el cliente existe
			DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
			return daoFactura.listarFacturasPorCliente(id_cliente);
		} else {
			return null;
		}
	}

	public TFacturasCliente listarFacturasConCliente(int id_cliente) {
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		TCliente cliente = daoCliente.buscarCliente(id_cliente);
		if (cliente != null) {
			DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
			// creamos nuestro TFacturasCliente para poder devolver la coleccion y el
			// TCliente a la vez
			return new TFacturasCliente(cliente, daoFactura.listarFacturasPorCliente(id_cliente));
		} else
			return null;
	}

	public boolean devolucionFactura(TLineaFactura lf) {
		// creamos los DAO
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
		DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		// buscamos la factura
		TFacturaLineaFacturas factura_lineasFactura = buscarFactura(lf.getIdFactura());
		if (factura_lineasFactura != null) {
			// modificamos la linea de factura
			int cantidad_quitada = daol.modificarLineaFactura(lf, factura_lineasFactura.getLineasFactura());
			if (cantidad_quitada != -1) {
				TProducto producto = daoProducto.buscarProducto(lf.getIdProducto());
				// sabemos que el producto existe porque todas las lineas de la factura estan
				// validadas
				producto.setStock(producto.getStock() + cantidad_quitada);
				daoProducto.actualizarProducto(producto);
				// modificamos la factura
				double nuevo_precio = factura_lineasFactura.getTFactura().getPrecio_total()
						- (cantidad_quitada * producto.getPrecio());
				factura_lineasFactura.getTFactura().setPrecio_total(nuevo_precio);
				return daoFactura.devolucionFactura(factura_lineasFactura.getTFactura());
			}
		}
		return false;
	}

}
