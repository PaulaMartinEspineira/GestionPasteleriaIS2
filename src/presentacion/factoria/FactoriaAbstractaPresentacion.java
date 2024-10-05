package presentacion.factoria;

import presentacion.Evento;
import presentacion.IGUI;

public abstract class FactoriaAbstractaPresentacion {
	
	private static FactoriaAbstractaPresentacion instance;
	
	public static FactoriaAbstractaPresentacion getInstance() {
		if (instance == null) {
			instance = new FactoriaPresentacion();
		}
		return instance;
	}
	
	public abstract IGUI createVista(Evento id);

}
