package presentacion.Controlador;
import presentacion.Evento;

public abstract class Controlador {
	private static Controlador instance = null;
	
	public static Controlador getInstance() {
		if(instance == null) {
			instance = new ControladorImp();
		}
		return instance;
	}
	
	public abstract void accion(Evento e, Object datos);
	
	
	
}