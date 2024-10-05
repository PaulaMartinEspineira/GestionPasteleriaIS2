package presentacion.GUIFacturas;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
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
import negocio.Producto.TProducto;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.ControladorImp;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class VistaAnadirProducto extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	private JTextField _tFIdProducto;
	private JSpinner _jSCantidad;
	private JTextField _tFPrecio;
	private Map<Integer, TProducto> _mapaProductos; // id producto, producto

	public VistaAnadirProducto() {
		this._mapaProductos = new HashMap<>();
		initGUI();
	}

	private void initGUI() {
		setTitle("Anadir Producto");

		// creamos los paneles
		JPanel _mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));

		JPanel _anadirPanel = new JPanel();
		_anadirPanel.setLayout(new BoxLayout(_anadirPanel, BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		_mainPanel.add(_anadirPanel);

		// ID producto
		JLabel idLabel = new JLabel("Id producto: ");
		_tFIdProducto = new JTextField();
		_tFIdProducto.setPreferredSize(new Dimension(100, 20));
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(idLabel);
		nombrePanel.add(_tFIdProducto);
		_anadirPanel.add(nombrePanel);

		// cantidad del producto
		JLabel cantidadLabel = new JLabel("Cantidad: ");
		_jSCantidad = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
		_jSCantidad.setPreferredSize(new Dimension(100, 20));
		JPanel cantidadPanel = new JPanel();
		cantidadPanel.add(cantidadLabel);
		cantidadPanel.add(_jSCantidad);
		_anadirPanel.add(cantidadPanel);

		// precio que costaria
		JLabel precioLabel = new JLabel("Precio: ");
		_tFPrecio = new JTextField();
		_tFPrecio.setEditable(false);
		_tFPrecio.setPreferredSize(new Dimension(100, 20));
		JPanel precioPanel = new JPanel();
		nombrePanel.add(precioLabel);
		nombrePanel.add(_tFPrecio);
		_anadirPanel.add(precioPanel);

		// cada vez que cambie la cantidad cambiara el precio
		_jSCantidad.addChangeListener((e) -> {
			int id_prod;
			int cantidad;
			boolean id_valido = true;
			try {
				id_prod = Integer.parseInt(this._tFIdProducto.getText());
				TProducto producto = this._mapaProductos.get(id_prod);
				if (producto == null) {
					id_valido = false;
				} else {// si existe el producto seleccionado podemos calcular su precio
					try {
						cantidad = Integer.parseInt(this._jSCantidad.getValue().toString());
						// si el producto y la cantidad so validos calculamos lo que costaria
						double precio = producto.getPrecio() * cantidad;
						_tFPrecio.setText(String.format("%.2f", precio));
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(this, "Debes indicar una cantidad valida", "Anadir Producto",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (Exception exception) {
				id_valido = false;
			}
			if (!id_valido) {
				JOptionPane.showMessageDialog(this, "Debes indicar un id de producto valido", "Anadir Producto",
						JOptionPane.ERROR_MESSAGE);
			}

		});

		// Aceptar y cancelar
		JPanel btnPanel = new JPanel();
		JButton acceptBtn = new JButton("Aceptar");
		acceptBtn.addActionListener((e) -> {
			anadirProducto();
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

	private void anadirProducto() {
		int id_prod;
		int cantidad;
		try {
			id_prod = Integer.parseInt(this._tFIdProducto.getText());
			cantidad = Integer.parseInt(this._jSCantidad.getValue().toString());
			if (cantidad <= 0) {
				JOptionPane.showMessageDialog(this, "Debes indicar una cantidad valida", "Anadir Producto",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// si todo es valido crea la linea de factura
			TLineaFactura linea = new TLineaFactura(id_prod, 0, 0, cantidad, 0, true);
			if (this._mapaProductos.containsKey(id_prod)) {
				ControladorImp.carrito.anadirProducto(linea);
				FactoriaAbstractaPresentacion.getInstance().createVista(Evento.VISTA_ANADIR_PRODUCTO)
						.actualizar(Evento.ANADIR_PRODUCTO_SUCCESS, "producto anadido al carrito con exito");
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(Evento.VISTA_ANADIR_PRODUCTO)
						.actualizar(Evento.ANADIR_PRODUCTO_ERROR, "producto no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Debes indicar un id de producto valido", "Anadir Producto",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case ANADIR_PRODUCTO_CARGA:// cargamos los datos de los productos y su precio
			ArrayList<TProducto> productos = (ArrayList<TProducto>) datos;
			this._mapaProductos.clear();
			for (TProducto p : productos) {
				this._mapaProductos.put(p.getId(), p);
			}
			break;
		case ANADIR_PRODUCTO_SUCCESS:
			JOptionPane.showMessageDialog(this, datos, "Anadir Producto", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case ANADIR_PRODUCTO_ERROR:
			JOptionPane.showMessageDialog(this, datos, "Anadir Producto", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
	}
}
