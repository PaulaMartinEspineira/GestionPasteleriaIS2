package presentacion.GUIEmpleados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.Empleados.TEmpleado;


public class ModeloTablaEmpleados extends AbstractTableModel{

	
	 
	private static final long serialVersionUID = 1L;

	private List<TEmpleado> empleados;
	
	//NO SE SI AÑADIR NUMVENTAS... lo tengo como atribtuo
	private String[] headers = { "Id", "DNI", "Nombre", "Apellido","Email", "Numero de Telefono", "Direccion","NumVentas", "Activo" };

	public ModeloTablaEmpleados() {
		empleados = new ArrayList<TEmpleado>();
	}

	@Override
	public int getRowCount() {
		return empleados.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public String getColumnName(int col) {
		return headers[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TEmpleado employee = empleados.get(rowIndex);
		if (columnIndex == 0) {
			return employee.getId();
		} 
		else if (columnIndex == 1) {
			return employee.getDNI();
		} 
		else if (columnIndex == 2) {
			return employee.getNombre();
		}
		
		else if (columnIndex == 3) {
			return employee.getApellido();
		}
		
		else if (columnIndex==4) {
			return employee.getCorreo();
		}

		else if (columnIndex == 5) {
			return employee.getNumTelefono();
		}
		else if (columnIndex == 6) {
			return employee.getDireccion();
		}
		else if (columnIndex == 7) {
			return employee.getNumVentas();
		}
		else if (columnIndex == 8) {
			return employee.getActivo();
		}
		else return "Se ha producido un error";
	}

	public void loadData(Collection<TEmpleado> data) {
		if(data!=null) { //ya habria empleados 
			empleados = new ArrayList<TEmpleado>(data);
		}
		fireTableDataChanged();
		fireTableStructureChanged();
	}


}
