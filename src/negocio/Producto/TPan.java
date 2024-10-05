package negocio.Producto;

public class TPan extends TProducto{
	
	boolean sal;
	boolean integral;

	public TPan(String nombre, double precio, String alergenos, int stock, int marcaId, boolean sal, boolean integral) {
		super(nombre, precio, alergenos, stock, "Pan", marcaId);
		this.sal = sal;
		this.integral = integral;
	}
	
	public boolean getSal() {
		return this.sal;
	}
	
	public void setSal(boolean sal) {
		this.sal = sal;
	}

	public boolean getIntegral() {
		return this.integral;
	}
	
	public void setIntegral(boolean integral) {
		this.integral = integral;
	}


}
