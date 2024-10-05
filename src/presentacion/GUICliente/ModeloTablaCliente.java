package presentacion.GUICliente;

import javax.swing.table.AbstractTableModel;

import negocio.Cliente.TCliente;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class ModeloTablaCliente extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private String[] _headers = {"Nombre", "Apellidos", "DNI", "Correo", "Activo", "Id"};
	private List<TCliente> listaClientes;
	
	public ModeloTablaCliente() {
		this.listaClientes = new ArrayList<TCliente>();
	}

	@Override
	public int getRowCount() {
		return this.listaClientes.size();
	}

	@Override
	public int getColumnCount() {
		return this._headers.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return this._headers[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TCliente cliente = this.listaClientes.get(rowIndex);
		
		if (columnIndex == 0) {
			return cliente.getNombre();
		}
		else if (columnIndex == 1) {
			return cliente.getApellidos();
		}
		else if (columnIndex == 2) {
			return cliente.getDNI();
		}
		else if (columnIndex == 3) {
			return cliente.getCorreo();
		}
		else if (columnIndex == 4) {
			return cliente.getActivo();
		}
		else if (columnIndex == 5) {
			return cliente.getId();
		}
		
		return "error";
	}
	
	public void loadData(Collection<TCliente> data) {
		this.listaClientes = new ArrayList<TCliente>(data);
		fireTableDataChanged();
		fireTableStructureChanged();
	}

}
