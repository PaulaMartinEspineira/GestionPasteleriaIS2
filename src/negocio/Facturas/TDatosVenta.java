package negocio.Facturas;

import java.util.ArrayList;

public class TDatosVenta {
	private int id_cliente;
	private int id_vendedor;
	private String fecha;
	private ArrayList<TLineaFactura> carrito;

	public TDatosVenta(int id_c, int id_v, String fecha, ArrayList<TLineaFactura> prod) {
		this.id_cliente = id_c;
		this.id_vendedor = id_v;
		this.carrito = prod;
		this.fecha = fecha;
	}

//GETTERS
	public int getIdCliente() {
		return this.id_cliente;
	}

	public int getIdVendedor() {
		return this.id_vendedor;
	}

	public String getFecha() {
		return this.fecha;
	}

	public ArrayList<TLineaFactura> getCarrito() {
		return this.carrito;
	}

//SETTERS
	public void setIdCliente(int IdCliente) {
		this.id_cliente = IdCliente;
	}

	public void setIdVendedor(int IdVendedor) {
		this.id_vendedor = IdVendedor;
	}

	public void setFecha(String Fecha) {
		this.fecha = Fecha;
	}

	public void setCarrito(ArrayList<TLineaFactura> prods) {
		this.carrito = prods;
	}
}
