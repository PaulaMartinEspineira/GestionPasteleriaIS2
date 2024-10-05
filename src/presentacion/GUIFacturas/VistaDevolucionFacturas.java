package presentacion.GUIFacturas;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import negocio.Facturas.TLineaFactura;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaDevolucionFacturas extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JTextField _tFIdFactura;
	private JTextField _tFIdProducto;
	private JSpinner _jSCantidad;

	public VistaDevolucionFacturas() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Devolucion Factura");

		// creamos el panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		// Id factura
		JLabel idFacturaLabel = new JLabel("Id factura: ");
		_tFIdFactura = new JTextField();
		_tFIdFactura.setPreferredSize(new Dimension(100, 20));
		JPanel idFacturaPanel = new JPanel();
		idFacturaPanel.add(idFacturaLabel);
		idFacturaPanel.add(_tFIdFactura);
		mainPanel.add(idFacturaPanel);

		// Id producto
		JLabel idProductoLabel = new JLabel("Id producto: ");
		_tFIdProducto = new JTextField();
		_tFIdProducto.setPreferredSize(new Dimension(100, 20));
		JPanel idProductoPanel = new JPanel();
		idProductoPanel.add(idProductoLabel);
		idProductoPanel.add(_tFIdProducto);
		mainPanel.add(idProductoPanel);

		// Cantidad
		JLabel cantidadLabel = new JLabel("Cantidad: ");
		_jSCantidad = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
		_jSCantidad.setPreferredSize(new Dimension(100, 20));
		JPanel cantidadPanel = new JPanel();
		cantidadPanel.add(cantidadLabel);
		cantidadPanel.add(_jSCantidad);
		mainPanel.add(cantidadPanel);

		// Aceptar y cancelar
		JPanel endPanel = new JPanel();
		JButton acceptBtn = new JButton("Aceptar");
		acceptBtn.addActionListener((e) -> {
			devolucionFactura();
			dispose();
		});
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((e) -> dispose());
		endPanel.add(acceptBtn);
		endPanel.add(cancelBtn);
		mainPanel.add(endPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void devolucionFactura() {
		int id_factura;
		try {
			id_factura = Integer.parseInt(this._tFIdFactura.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Debes indicar un id de factura valido", "Devolucion Factura",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		int id_producto;
		try {
			id_producto = Integer.parseInt(this._tFIdProducto.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Debes indicar un id de producto valido", "Devolucion Factura",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		int cantidad = Integer.parseInt(this._jSCantidad.getValue().toString());
		if (cantidad <= 0) {
			JOptionPane.showMessageDialog(this, "Debes indicar una cantidad valida", "Devolucion Factura",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// si todos los datos son correctos creamos la linea de factura con los datos a quitar
		TLineaFactura linea_factura = new TLineaFactura(id_producto, id_factura, 0, cantidad, 0, true);
		Controlador.getInstance().accion(Evento.DEVOLUCION_FACTURA, linea_factura);
	}

	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case DEVOLUCION_FACTURA_SUCCESS:
			JOptionPane.showMessageDialog(this, "factura con id " + datos + " modificada con éxito.",
					"Devolucion Factura", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case DEVOLUCION_FACTURA_ERROR:
			JOptionPane.showMessageDialog(this, "Error al modificar la factura: " + datos, "Devolucion Factura",
					JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
	}

}
