package presentacion.GUIFacturas;

import java.awt.BorderLayout;

import java.awt.Color;
import negocio.Facturas.TLineaFactura;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.ControladorImp;

public class VistaVerCarrito extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private ModeloTablaLineaFacturaCarrito _modeloTabla;

	public VistaVerCarrito() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Ver Carrito");

		// Creamos el panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		// Creamos la tabla
		this._modeloTabla = new ModeloTablaLineaFacturaCarrito();
		JTable table = new JTable(this._modeloTabla);
		JPanel tablePanel = new JPanel();
		mainPanel.add(tablePanel);

		ArrayList<TLineaFactura> lf = ControladorImp.carrito.getProductos();
		_modeloTabla.loadData(lf);

		tablePanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), "Carrito"));
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
		;
	}

}
