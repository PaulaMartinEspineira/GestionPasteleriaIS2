package presentacion.GUIFacturas;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import negocio.Facturas.TLineaFactura;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.ControladorImp;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class VistaEliminarProducto extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	private JTextField _tFIdProducto;
	private JSpinner _jSCantidad;

	public VistaEliminarProducto() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Eliminar Producto");

		// Creamos los paneles
		JPanel _mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));

		JPanel _anadirPanel = new JPanel();
		_anadirPanel.setLayout(new BoxLayout(_anadirPanel, BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		_mainPanel.add(_anadirPanel);

		// Id producto
		JLabel idLabel = new JLabel("Id producto: ");
		_tFIdProducto = new JTextField();
		_tFIdProducto.setPreferredSize(new Dimension(100, 20));
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(idLabel);
		nombrePanel.add(_tFIdProducto);
		_anadirPanel.add(nombrePanel);

		// Cantidad
		JLabel cantidadLabel = new JLabel("Cantidad: ");
		_jSCantidad = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
		_jSCantidad.setPreferredSize(new Dimension(100, 20));
		JPanel cantidadPanel = new JPanel();
		cantidadPanel.add(cantidadLabel);
		cantidadPanel.add(_jSCantidad);
		_anadirPanel.add(cantidadPanel);

		// Aceptar y cancelar
		JPanel btnPanel = new JPanel();
		JButton acceptBtn = new JButton("Aceptar");
		acceptBtn.addActionListener((e) -> {
			eliminarProducto();
			dispose();
		});
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((e) -> dispose());
		btnPanel.add(acceptBtn);
		btnPanel.add(cancelBtn);
		_anadirPanel.add(btnPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void eliminarProducto() {
		int id_prod;
		int cantidad;
		try {
			id_prod = Integer.parseInt(this._tFIdProducto.getText());
			cantidad = Integer.parseInt(this._jSCantidad.getValue().toString());
			if (cantidad <= 0) {
				JOptionPane.showMessageDialog(this, "Debes indicar una cantidad valida", "Eliminar Producto",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// si todo es correcto creamos la linea de factura de producto a quitar
			TLineaFactura linea = new TLineaFactura(id_prod, 0, 0, cantidad, 0,true);
			if (ControladorImp.carrito.eliminarProducto(linea)) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Evento.VISTA_ELIMINAR_PRODUCTO)
						.actualizar(Evento.ELIMINAR_PRODUCTO_SUCCESS, "producto eliminado del carrito con exito");
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(Evento.VISTA_ELIMINAR_PRODUCTO)
						.actualizar(Evento.ELIMINAR_PRODUCTO_ERROR, "producto no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Debes indicar un id de producto valido", "Eliminar Producto",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case ELIMINAR_PRODUCTO_SUCCESS:
			JOptionPane.showMessageDialog(this, datos, "Eliminar Producto", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case ELIMINAR_PRODUCTO_ERROR:
			JOptionPane.showMessageDialog(this, datos, "Eliminar Producto", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
	}
}
