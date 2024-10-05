package negocio.Producto;

public class TBebida extends TProducto{
	
	private String tamanyo;

	public TBebida(String nombre, double precio, String alergenos, int stock, int marcaId, String tamanyo) {
		super(nombre, precio, alergenos, stock, "Bebida", marcaId);
		this.tamanyo = tamanyo;
	}
	
	public String getTamanyo() {
		return this.tamanyo;
	}
	
	public void setTamanyo(String tamanyo) {
		this.tamanyo = tamanyo;
	}


}
