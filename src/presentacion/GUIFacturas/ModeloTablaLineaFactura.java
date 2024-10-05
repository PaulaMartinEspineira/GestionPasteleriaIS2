package presentacion.GUIFacturas;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;
import negocio.Facturas.TLineaFactura;

public class ModeloTablaLineaFactura extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<TLineaFactura> linea_facturas;
	private String[] headers = { "Id linea", "Id producto", "Cantidad", "Precio", "Activa" };

	public ModeloTablaLineaFactura() {
		linea_facturas = new ArrayList<TLineaFactura>();
	}

	public int getRowCount() {
		return linea_facturas.size();
	}

	public int getColumnCount() {
		return headers.length;
	}

	public String getColumnName(int col) {
		return headers[col];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		TLineaFactura lf = linea_facturas.get(rowIndex);
		if (columnIndex == 0) {
			return lf.getIdLinea();
		} else if (columnIndex == 1) {
			return lf.getIdProducto();
		} else if (columnIndex == 2) {
			return lf.getCantidad();
		} else if (columnIndex == 3) {
			return lf.getPrecio();
		} else if (columnIndex == 4) {
			return lf.getActivo();
		}
		return "error";
	}

	public void loadData(Collection<TLineaFactura> data) {
		if (data != null) {// solo si hay facturas en la lista
			linea_facturas = new ArrayList<TLineaFactura>(data);
		}
		fireTableDataChanged();
		fireTableStructureChanged();
	}

}
