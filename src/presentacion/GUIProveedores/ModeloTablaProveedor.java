package presentacion.GUIProveedores;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.Proveedor.TProveedor;

public class ModeloTablaProveedor extends AbstractTableModel {
	
		private static final long serialVersionUID = 1L;
		
		private List<TProveedor> proveedores;
		private String[] headers = {"ID", "Nombre", "Telefono", "Correo", "Código Postal"};

		public ModeloTablaProveedor() {
			proveedores = new ArrayList<TProveedor>();
		}

		@Override
		public int getRowCount() {
			return proveedores.size();
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
			TProveedor proveedor = proveedores.get(rowIndex);
			if (columnIndex == 0) {
				return proveedor.getID();
			} 
			else if (columnIndex == 1) {
				return proveedor.getNombre();
			} 
			else if (columnIndex == 2) {
				return proveedor.getTelefono();
			}
			else if (columnIndex == 3) {
				return proveedor.getCorreo();
			}
			else if (columnIndex == 4) {
				return proveedor.getCodigoPostal();
			}
			else if(columnIndex == 5) {
				return proveedor.getID();
			}
			else return "error";
		}

		public void loadData(Collection<TProveedor> data) {
			if(data!=null) {
				proveedores = new ArrayList<TProveedor>(data);
			}
			fireTableDataChanged();
			fireTableStructureChanged();
		}

}
