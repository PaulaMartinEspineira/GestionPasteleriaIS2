package presentacion.GUIProducto;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Producto.TProducto;
import negocio.Producto.TDulce;
import negocio.Producto.TPan;
import negocio.Producto.TBebida;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBuscarProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 6660804707223318345L;
	JTextField _tFNombre;
	JPanel _pedirNombrePanel;
	JPanel _infoPanel;
	JPanel _mainPanel;

	public VistaBuscarProducto() {
		initGUI();
	}
	
	void initGUI() {
		setTitle("Buscar Producto");

		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		_pedirNombrePanel = new JPanel();
		_pedirNombrePanel.setLayout(new BoxLayout(_pedirNombrePanel, BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		_mainPanel.add(_pedirNombrePanel);
		
		JLabel nombreLabel = new JLabel("Nombre: ");
		_tFNombre = new JTextField();
		_tFNombre.setPreferredSize(new Dimension(100, 20));
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(nombreLabel);
		nombrePanel.add(_tFNombre);
		_pedirNombrePanel.add(nombrePanel);
		
		JPanel btnPanel = new JPanel();
		JButton acceptBtn = new JButton("Buscar");
		acceptBtn.addActionListener((e)-> buscarProducto());
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((e)-> dispose());
		btnPanel.add(acceptBtn);
		btnPanel.add(cancelBtn);
		_pedirNombrePanel.add(btnPanel);
		
	    setPreferredSize(new Dimension(300, 250));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	void initInfoGUI(TProducto producto) {
		_infoPanel = new JPanel();
		_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));
		_mainPanel.add(_infoPanel);

		
		JLabel nombreLabel = new JLabel("Nombre: " + producto.getNombre());
		JLabel idLabel = new JLabel("ID: " + producto.getId());
		JLabel stockLabel = new JLabel("Stock: " + producto.getStock());
		JLabel precioLabel = new JLabel("Precio: " + producto.getPrecio());
		JLabel alergenosLabel = new JLabel("Alérgenos: " + producto.getAlergenos());
		JLabel tipoLabel = new JLabel("Tipo: " + producto.getTipo());
		JLabel marcaLabel = new JLabel("Marca: " + producto.getMarca());
		String activo = producto.getActivo() ? "sí" : "no";
		JLabel activoLabel = new JLabel("Activo: " + activo);
		_infoPanel.add(nombreLabel);
		_infoPanel.add(idLabel);
		_infoPanel.add(stockLabel);
		_infoPanel.add(precioLabel);
		_infoPanel.add(alergenosLabel);
		_infoPanel.add(marcaLabel);
		_infoPanel.add(activoLabel);
		_infoPanel.add(tipoLabel);
		
		if (producto.getTipo().equals("Dulce")) {
			JLabel rellenoLabel = new JLabel("Relleno: " + ((TDulce) producto).getRelleno());
			_infoPanel.add(rellenoLabel);
		}
		else if (producto.getTipo().equals("Bebida")) {
			JLabel tamanyoLabel = new JLabel("Tamaño: " + ((TBebida) producto).getTamanyo());
			_infoPanel.add(tamanyoLabel);
		}
		else {
			String integral, sal;
			integral = ((TPan) producto).getIntegral() ? "sí" : "no";
			sal = ((TPan) producto).getSal() ? "sí" : "no";
			JLabel integralLabel = new JLabel("Integral: " + integral);
			_infoPanel.add(integralLabel);
			JLabel salLabel = new JLabel("Sal: " + sal);
			_infoPanel.add(salLabel);
		}
		
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener((e) -> dispose());
		_infoPanel.add(continuarBtn);
		
		pack();
	}
	
	private void buscarProducto() {
		String nombre = _tFNombre.getText();
		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debes indicar un nombre", "Buscar Producto", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dispose();
		Controlador.getInstance().accion(Evento.BUSCAR_PRODUCTO, nombre);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {	
		switch(e) {
		case BUSCAR_PRODUCTO_SUCCESS:
			_pedirNombrePanel.setVisible(false);
			initInfoGUI((TProducto) datos);
			break;
		case BUSCAR_PRODUCTO_ERROR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos, "Buscar Producto", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
}
