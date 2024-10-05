package negocio.Marca;

public class TMarca {
	private int id;
	private String nombre;
	private String correo;
	private boolean activo;

	public TMarca(String nombre) {
		this.id = 0;
		this.nombre = nombre;
		this.correo = "";
		this.activo = true;
	}

	public TMarca(String nombre, String correo) {
		this.id = 0;
		this.nombre = nombre;
		this.correo = correo;
		this.activo = true;
	}

	public TMarca(int id, String nombre, String correo, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.activo = activo;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}