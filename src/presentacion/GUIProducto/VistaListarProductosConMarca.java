package presentacion.GUIProducto;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;

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

import negocio.Marca.TMarca;
import negocio.Producto.TProducto;
import negocio.Producto.TOAProductosMarca;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaListarProductosConMarca extends JFrame implements IGUI {
	private static final long serialVersionUID = -3264311669905931506L;
	private ModeloTablaProducto _modeloTabla;
	private JPanel _mainPanel;
	private JPanel _pedirNombrePanel;
	private JTextField _tFNombre;
	
	public VistaListarProductosConMarca() {
		initGUI();
	}
	
	void initGUI() {
		setTitle("Listar Productos con Marca");
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		_pedirNombrePanel = new JPanel();
		_pedirNombrePanel.setLayout(new BoxLayout(_pedirNombrePanel, BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		_mainPanel.add(_pedirNombrePanel);
		
		JLabel nombreLabel = new JLabel("Nombre de la marca: ");
		_tFNombre = new JTextField();
		_tFNombre.setPreferredSize(new Dimension(100, 20));
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(nombreLabel);
		nombrePanel.add(_tFNombre);
		_pedirNombrePanel.add(nombrePanel);
		
		JPanel btnPanel = new JPanel();
		JButton acceptBtn = new JButton("Listar Productos");
		acceptBtn.addActionListener((e)-> listarConMarca());
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((e)-> dispose());
		btnPanel.add(acceptBtn);
		btnPanel.add(cancelBtn);
		_pedirNombrePanel.add(btnPanel);
		
	    setPreferredSize(new Dimension(700, 400));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	void listarConMarca() {
		String nombre = _tFNombre.getText();
		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debes indicar un nombre de marca", "Listar Productos con Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dispose();
		Controlador.getInstance().accion(Evento.LISTAR_PRODUCTOS_CON_MARCA, nombre);
	}
	
	void initGUIListado(TMarca marca) {
		_pedirNombrePanel.setVisible(false);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		JLabel infoLabel = new JLabel("Información sobre la marca: ");
		JLabel idLabel = new JLabel("Id: " + marca.getID());
		JLabel nombreLabel = new JLabel("Nombre: " + marca.getNombre());
		JLabel correoLabel = new JLabel("Correo: " + marca.getCorreo());
		String activo = marca.getActivo() ? "Si" : "No";
		JLabel activoLabel = new JLabel("Activo: " + activo);
		infoPanel.add(infoLabel);
		infoPanel.add(idLabel);
		infoPanel.add(nombreLabel);
		infoPanel.add(correoLabel);
		infoPanel.add(activoLabel);
		_mainPanel.add(infoPanel);
		
		_modeloTabla = new ModeloTablaProducto();
		JTable table = new JTable(_modeloTabla);
		JPanel tablePanel = new JPanel();
		_mainPanel.add(tablePanel);
		
		tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), "Productos"));
		tablePanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener((e) -> dispose());
		_mainPanel.add(continuarBtn);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case LISTAR_PRODUCTOS_CON_MARCA_SUCCESS:
			TOAProductosMarca productosMarca = (TOAProductosMarca) datos;
			Collection<TProducto> productos = productosMarca.getProductos();
			TMarca marca = productosMarca.getMarca();
			initGUIListado(marca);
			_modeloTabla.loadData(productos);
			break;
		case LISTAR_PRODUCTOS_CON_MARCA_ERROR:
			JOptionPane.showMessageDialog(this, "Error al listar productos: " + datos, "Listar Productos Con Marca", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}