package presentacion.GUIFacturas;

import java.awt.BorderLayout;
import java.awt.Color;
import negocio.Facturas.TFactura;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import presentacion.Evento;
import presentacion.IGUI;

public class VistaListarFacturas extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private ModeloTablaFacturas _modeloTabla;

	public VistaListarFacturas() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Listar Facturas");

		// Creamos el panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		// Creamos la tabla
		this._modeloTabla = new ModeloTablaFacturas();
		JTable table = new JTable(this._modeloTabla);
		JPanel tablePanel = new JPanel();
		mainPanel.add(tablePanel);

		tablePanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), "Facturas"));
		tablePanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		// Continuar
		JPanel continuarPanel = new JPanel();
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener((e) -> dispose());
		continuarPanel.add(continuarBtn);
		mainPanel.add(continuarPanel, BorderLayout.PAGE_END);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case LISTAR_FACTURAS:
			Collection<TFactura> facturas = (Collection<TFactura>) datos;
			_modeloTabla.loadData(facturas);
			break;
		default:
			break;
		}
	}

}
