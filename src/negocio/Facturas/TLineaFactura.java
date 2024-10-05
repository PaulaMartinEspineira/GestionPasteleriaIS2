package negocio.Facturas;

public class TLineaFactura {
	private int id_producto;
	private int cantidad;
	private int id_factura;
	private int id_linea;
	private double precio;
	private boolean activa;

	public TLineaFactura(int id_prod, int id_fact, int id, int cant, double precio, boolean act) {
		this.id_producto = id_prod;
		this.cantidad = cant;
		this.id_factura = id_fact;
		this.id_linea = id;
		this.precio = precio;
		this.activa = act;
	}

//GETTERS
	public int getIdProducto() {
		return this.id_producto;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public int getIdFactura() {
		return this.id_factura;
	}

	public int getIdLinea() {
		return this.id_linea;
	}

	public double getPrecio() {
		return this.precio;
	}

	public boolean getActivo() {
		return this.activa;
	}

//SETTERS
	public void setIdProducto(int id_prod) {
		this.id_producto = id_prod;
	}

	public void setCantidadProducto(int cant) {
		this.cantidad = cant;
	}

	public void setIdFactura(int id_fact) {
		this.id_factura = id_fact;
	}

	public void setIdLinea(int id) {
		this.id_linea = id;
	}

	public void setPrecio(double p) {
		this.precio = p;
	}

	public void setActivo(boolean activo) {
		this.activa = activo;
	}
}
