package negocio.Empleados;

import java.util.Collection;


import integracion.Empleado.DAOEmpleado;
import integracion.Factoria.FactoriaAbstractaIntegracion;



//el SA siempre se crea un DAO
//El DAO es el que hará las operaciones CRUD

public class SAEmpleadoImp implements SAEmpleado {

	@Override
	public int altaEmpleado(TEmpleado empleado) throws IllegalArgumentException {
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();
		TEmpleado empl = daoEmpleado.buscarEmpleado(empleado.getDNI()); // vamos a buscarlo por el DNI

		// vamos a buscarlo por el DNI
		int id = -1;

		// el empleado no está en nuestro fichero JSON
		if (empl == null) {
			id = daoEmpleado.altaEmpleado(empleado);
		}

		// como tenemos un atributo activo tb tenemos que mirar
		// mirar si está activo o inactivo
		else if (!empl.getActivo()) { // el empleado está, pero dado de baja
			empleado.setId(empl.getId());
			empleado.setActivo(true);
			id = daoEmpleado.actualizarEmpleado(empl);
		} else {
			throw new IllegalArgumentException("El empleado ya existe.");
		}

		return id;
	}

	@Override
	public int bajaEmpleado(String dni) throws IllegalArgumentException {
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();
		TEmpleado empl = daoEmpleado.buscarEmpleado(dni); // vamos a buscarlo por el DNI

		if (empl == null) { // El empleado no existe
			throw new IllegalArgumentException("El empleado no existe.");
		}

		// tenemos que ver si el empleado ha sido ya dado de baja previamente
		else if (!empl.getActivo()) {
			throw new IllegalArgumentException("El empleado ya ha sido dado de baja.");
		}
		daoEmpleado.bajaEmpleado(empl.getId()); // else, damos de baja al empledao
		return empl.getId();
	}
	

	

	@Override
	public int actualizarEmpleado(TEmpleado empleado) {
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();
		TEmpleado empl = daoEmpleado.buscarEmpleado(empleado.getDNI()); // vamos a buscarlo por el DNI

		if (empl == null) { // El empleado no existe
			throw new IllegalArgumentException("El empleado no existe.");
		}

		empleado.setId(empl.getId());
		empleado.setNombre(empl.getNombre());
		empleado.setApellidos(empl.getApellido());
		empleado.setNumVentas(empl.getNumVentas());
		return daoEmpleado.actualizarEmpleado(empleado); // lo actualizamos
	}
	



	@Override
	public TEmpleado buscarEmpleado(String dni) {
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();
		return daoEmpleado.buscarEmpleado(dni);
	}

	@Override
	public Collection<TEmpleado> listarEmpleados() {
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();
		return daoEmpleado.listarEmpleados();
	}

}
