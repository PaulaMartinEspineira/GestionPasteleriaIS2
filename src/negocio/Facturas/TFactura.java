package negocio.Facturas;

public class TFactura {
	private double precio_total;
	private boolean activo;
	private int id_factura;
	private int id_vendedor;
	private int id_cliente;
	private String fecha;

	public TFactura(int id_f, int id_c, int id_v, double total,String fecha,boolean Activo) {
		this.id_factura = id_f;
		this.id_cliente = id_c;
		this.id_vendedor = id_v;
		this.precio_total = total;
		this.activo = Activo;
		this.fecha = fecha;
	}

//GETTERS	
	public int getIdFactura() {
		return this.id_factura;
	}

	public int getIdCliente() {
		return this.id_cliente;
	}

	public int getIdVendedor() {
		return this.id_vendedor;
	}

	public String getFecha() {
		return this.fecha;
	}

	public double getPrecio_total() {
		return this.precio_total;
	}

	public boolean getActivo() {
		return this.activo;
	}

//SETTERS
	public void setIdFactura(int Id) {
		this.id_factura = Id;
	}

	public void setIdCliente(int Id) {
		this.id_cliente = Id;
	}

	public void setIdVendedor(int Id) {
		this.id_vendedor = Id;
	}

	public void setFecha(String Fecha) {
		this.fecha = Fecha;
	}

	public void setPrecio_total(double Precio_total) {
		this.precio_total = Precio_total;
	}

	public void setActivo(boolean Activo) {
		this.activo = Activo;
	}

}
