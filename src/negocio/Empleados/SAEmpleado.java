package negocio.Empleados;

import java.util.Collection;


//en la interfaz solo se pondrá el nombre de los metodos
//la correspondiente implementacion irá en SAEmpleadoImp, dentro del mismo paquete 
public interface SAEmpleado {
	//vamos a devolver un entero si ha podido realizar o no las operaciones
	//las operaciones de ALTA, BAJA, ACTUALIZAR
	
   public int altaEmpleado(TEmpleado empleado);
   public int bajaEmpleado(String dni);
   public int actualizarEmpleado(TEmpleado empleado); //vamos a buscar por dni
   public TEmpleado buscarEmpleado (String dni);
   public Collection<TEmpleado> listarEmpleados();

}
