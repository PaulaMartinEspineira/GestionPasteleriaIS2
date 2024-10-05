package negocio.Cliente;

public class TCliente {
	private String nombre;
	private String apellidos;
	private String dni;
	private String correo;
	private boolean activo;
	private int id;
	
	
	public TCliente(String nombre, String apellidos, String dni, String correo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.correo = correo;
		this.activo = true;
		this.id = 0;
	}
	
	public TCliente(String nombre, String apellidos, String dni, String correo, boolean activo, int id) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.correo = correo;
		this.activo = activo;
		this.id = id;
	}
	
	
	// Getters.
	public String getNombre() {
		return this.nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public String getDNI() {
		return this.dni;
	}

	public String getCorreo() {
		return this.correo;
	}

	public boolean getActivo() {
		return this.activo;
	}
	
	public int getId() {
		return this.id;
	}
	
	// Setters.
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public void setActivo(boolean activo) {
		this.activo=activo;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
