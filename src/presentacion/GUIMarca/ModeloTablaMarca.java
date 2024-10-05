package presentacion.GUIMarca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.Marca.TMarca;

public class ModeloTablaMarca extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<TMarca> marcas;
	private String[] headers = { "Id", "Nombre", "Correo" };

	public ModeloTablaMarca() {
		marcas = new ArrayList<TMarca>();
	}

	@Override
	public int getRowCount() {
		return marcas.size();
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
		TMarca marca = marcas.get(rowIndex);
		if (columnIndex == 0) {
			return marca.getID();
		} 
		else if (columnIndex == 1) {
			return marca.getNombre();
		} 
		else if (columnIndex == 2) {
			return marca.getCorreo();
		}
		else return "error";
	}

	public void loadData(Collection<TMarca> data) {
		if(data!=null) {//si ya hay marcas en la lista
			marcas = new ArrayList<TMarca>(data);
		}
		fireTableDataChanged();
		fireTableStructureChanged();
	}


}
