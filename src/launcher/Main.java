package launcher;

import presentacion.Evento;
import presentacion.Controlador.Controlador;

public class Main {
	public static void main(String[] args) {
		Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);		
	}
}
