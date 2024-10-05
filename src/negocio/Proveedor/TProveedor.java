package negocio.Proveedor;

public class TProveedor {

		private int id;
		private String nombre;
		private int telefono;
		private String correo;
		private int codigoPostal;
		private boolean activo;

		public TProveedor() {
			this.id = 0; 
			this.nombre = "";
			this.telefono = -1;
			this.correo = "";
			this.codigoPostal = -1;
			this.activo = true;
		}

		public TProveedor(String nombre, int telefono, String correo, int codigoPostal) { //para alta
			this.id = 0;
			this.nombre = nombre;
			this.telefono = telefono;
			this.correo = correo;
			this.codigoPostal = codigoPostal;
			this.activo = true;
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
		
		public int getTelefono() {
			return this.telefono;
		}
		
		public void setTelefono(int telefono) {
			this.telefono= telefono;
		}
		
		public String getCorreo() {
			return this.correo;
		}
		
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		
		public int getCodigoPostal() {
			return this.codigoPostal ;
		}
		
		public void setCodigoPostal(int codigoPostal) {
			this.codigoPostal = codigoPostal;
		}
		
		public boolean getActivo() {
			return this.activo;
		}
		
		public void setActivo(boolean activo) {
			this.activo = activo;
		}
}
