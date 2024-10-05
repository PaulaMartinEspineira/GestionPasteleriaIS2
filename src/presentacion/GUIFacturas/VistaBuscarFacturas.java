package presentacion.GUIFacturas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import negocio.Facturas.TFactura;
import negocio.Facturas.TLineaFactura;
import negocio.Facturas.TFacturaLineaFacturas;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBuscarFacturas extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JTextField _tFIdFactura;
	private JPanel _pedirIdFacturaPanel;
	private JPanel _mainPanel;

	public VistaBuscarFacturas() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Buscar Factura");

		// creamos los paneles
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		_pedirIdFacturaPanel = new JPanel();
		_pedirIdFacturaPanel.setLayout(new BoxLayout(_pedirIdFacturaPanel, BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		_mainPanel.add(_pedirIdFacturaPanel);

		// Id factura
		_tFIdFactura = new JTextField();
		_tFIdFactura.setPreferredSize(new Dimension(100, 20));
		JPanel idFacturaPanel = new JPanel();
		idFacturaPanel.add(new JLabel("Id factura: "));
		idFacturaPanel.add(_tFIdFactura);
		_pedirIdFacturaPanel.add(idFacturaPanel);

		// Buscar y cancelar
		JPanel btnPanel = new JPanel();
		JButton acceptBtn = new JButton("Buscar");
		acceptBtn.addActionListener((e) -> {
			buscarFactura();
			dispose();
		});

		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((e) -> dispose());

		btnPanel.add(acceptBtn);
		btnPanel.add(cancelBtn);
		_pedirIdFacturaPanel.add(btnPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initInfoGUI(TFacturaLineaFacturas factura_lineasFactura) {

		JPanel _infoPanel = new JPanel();
		_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));
		_mainPanel.add(_infoPanel);

		// Creamos la tabla
		ModeloTablaLineaFactura _modeloTabla = new ModeloTablaLineaFactura();
		JTable table = new JTable(_modeloTabla);
		JPanel tablePanel = new JPanel();

		tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
				"Lineas de Facturas"));
		tablePanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		// Imprimimos toda la informacion de la factura
		// la factura
		TFactura factura = factura_lineasFactura.getTFactura();
		_infoPanel.add(new JLabel("INFORMACION FACTURA"));
		_infoPanel.add(new JLabel("Id: " + factura.getIdFactura()));
		_infoPanel.add(new JLabel("Precio: " + factura.getPrecio_total()));
		_infoPanel.add(new JLabel("Fecha: " + factura.getFecha()));
		_infoPanel.add(new JLabel("Id cliente: " + factura.getIdCliente()));
		_infoPanel.add(new JLabel("Id vendedor: " + factura.getIdVendedor()));
		_infoPanel.add(new JLabel("Activa: " + factura.getActivo()));
		// las lineas de factura
		ArrayList<TLineaFactura> lineas_factura = factura_lineasFactura.getLineasFactura();
		if (lineas_factura.size() > 0) {
			_infoPanel.add(tablePanel);
			_modeloTabla.loadData(lineas_factura);
		}

		// continuar
		JPanel continuarPanel = new JPanel();
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener((e) -> dispose());
		continuarPanel.add(continuarBtn);
		_mainPanel.add(continuarPanel, BorderLayout.PAGE_END);

		pack();
	}

	private void buscarFactura() {
		int id_factura;
		try {
			id_factura = Integer.parseInt(this._tFIdFactura.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Debes indicar un id de factura valido", "Buscar Factura",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Controlador.getInstance().accion(Evento.BUSCAR_FACTURA, id_factura);
	}

	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case BUSCAR_FACTURA_SUCCESS:
			_pedirIdFacturaPanel.setVisible(false);
			initInfoGUI((TFacturaLineaFacturas) datos);
			break;
		case BUSCAR_FACTURA_ERROR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos, "Buscar Factura", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
	}
}
