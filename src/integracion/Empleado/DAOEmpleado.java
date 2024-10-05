package integracion.Empleado;

import java.util.Collection;


import negocio.Empleados.TEmpleado;

public interface DAOEmpleado {

	   public int altaEmpleado(TEmpleado empleado);
	   public void bajaEmpleado(int id);
	   public TEmpleado buscarEmpleado (String dni);
	   public TEmpleado buscarEmpleado (int id);
	   public int actualizarEmpleado(TEmpleado empleado); //vamos a buscar por dni
	   public Collection<TEmpleado> listarEmpleados();
	   
}