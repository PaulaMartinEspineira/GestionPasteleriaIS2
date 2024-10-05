package presentacion.GUIProducto;

import java.util.List;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.Producto.TBebida;
import negocio.Producto.TDulce;
import negocio.Producto.TPan;
import negocio.Producto.TProducto;

public class ModeloTablaProducto extends AbstractTableModel{
	private static final long serialVersionUID = 6342360340874394881L;
	private List<TProducto> productos;
	private List<String> columnNames;
	
	public ModeloTablaProducto() {
		productos = new ArrayList<TProducto>();
		columnNames = new ArrayList<String>();
		columnNames.add("Nombre");
		columnNames.add("Id");
		columnNames.add("Stock");
		columnNames.add("Precio");
		columnNames.add("Alérgenos");
		columnNames.add("IdMarca");
		columnNames.add("Tipo");
		columnNames.add("Relleno");
		columnNames.add("Integral");
		columnNames.add("Sal");
		columnNames.add("Tamaño");
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames.get(col);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TProducto prod = productos.get(rowIndex);
		if (columnIndex == 0) {
			return prod.getNombre();
		}
		else if (columnIndex == 1) {
			return prod.getId();
		}
		else if (columnIndex == 2) {
			return prod.getStock();
		}
		else if (columnIndex == 3) {
			return prod.getPrecio();
		}
		else if (columnIndex == 4) {
			return prod.getAlergenos();
		}
		else if (columnIndex == 5) {
			return prod.getMarca();
		}
		else if (columnIndex == 6) {
			return prod.getTipo();
		}
		else if (columnIndex == 7) {
			if (prod.getTipo().equals("Dulce")) {
				return ((TDulce) prod).getRelleno();
			}
			else {
				return "NA";
			}
		}
		else if (columnIndex == 8) {
			if (prod.getTipo().equals("Pan")) {
				return ((TPan) prod).getIntegral();
			}
			else {
				return "NA";
			}
			
		}
		else if (columnIndex == 9) {
			if (prod.getTipo().equals("Pan")) {
				return ((TPan) prod).getSal();
			}
			else {
				return "NA";
			}
		}
		else if (columnIndex == 10) {
			if (prod.getTipo().equals("Bebida")) {
				return ((TBebida) prod).getTamanyo();
			}
			else {
				return "NA";
			}
		}
		return "error";
	}
	
	public void loadData(Collection<TProducto> data) {
		productos = new ArrayList<TProducto>(data);
		fireTableDataChanged();
		fireTableStructureChanged();
	}

}
