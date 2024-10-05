package negocio.Producto;

public class TProducto {
	private String nombre;
	private double precio;
	private String alergenos;
	private int stock;
	private String tipo;
	private int marcaId;
	private int id;
	private boolean activo;
	
	public TProducto(String nombre, double precio, String alergenos, int stock,String tipo, int marcaId ){
		this.nombre= nombre;
		this.precio= precio;
		this.alergenos = alergenos;
		this.stock = stock;
		this.tipo = tipo;
		this.marcaId = marcaId;
		this.id = -1;
		this.activo = true;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public void setPrecio(double precio) {
		this.precio= precio;
	}
	
	public String getAlergenos() {
		return this.alergenos;
	}
	
	public void setAlergenos(String alergenos) {
		this.alergenos = alergenos;
	}
	
	public int getStock() {
		return this.stock ;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getMarca() {
		return this.marcaId;
	}
	
	public void setMarca(int marcaId) {
		this.marcaId = marcaId;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getActivo() {
		return this.activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
