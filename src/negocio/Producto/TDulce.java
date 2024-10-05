package negocio.Producto;

public class TDulce extends TProducto {

	private String relleno;
	
	public TDulce(String nombre, double precio, String alergenos, int stock, int marcaId, String relleno) {
		super(nombre, precio, alergenos, stock, "Dulce", marcaId);
		this.relleno = relleno;
	}
	
	public String getRelleno() {
		return this.relleno;
	}
	
	public void setRelleno(String relleno) {
		this.relleno = relleno;
	}

}
