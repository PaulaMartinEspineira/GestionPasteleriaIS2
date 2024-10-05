package presentacion.GUIFacturas;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;

import negocio.Facturas.TFactura;

public class ModeloTablaFacturas extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<TFactura> facturas;
	private String[] headers = { "Id", "Precio", "Fecha", "Id cliente", "Id vendedor" };

	public ModeloTablaFacturas() {
		facturas = new ArrayList<TFactura>();
	}

	public int getRowCount() {
		return facturas.size();
	}

	public int getColumnCount() {
		return headers.length;
	}

	public String getColumnName(int col) {
		return headers[col];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		TFactura factura = facturas.get(rowIndex);
		if (columnIndex == 0) {
			return factura.getIdFactura();
		} else if (columnIndex == 1) {
			return factura.getPrecio_total();
		} else if (columnIndex == 2) {
			return factura.getFecha();
		} else if (columnIndex == 3) {
			return factura.getIdCliente();
		} else if (columnIndex == 4) {
			return factura.getIdVendedor();
		}
		return "error";
	}

	public void loadData(Collection<TFactura> data) {
		if (data != null) {// solo si hay facturas en la lista
			facturas = new ArrayList<TFactura>(data);
		}
		fireTableDataChanged();
		fireTableStructureChanged();
	}

}
