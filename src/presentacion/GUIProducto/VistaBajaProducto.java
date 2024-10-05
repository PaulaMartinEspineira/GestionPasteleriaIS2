package presentacion.GUIProducto;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBajaProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 8574972520720904000L;
	JTextField _tFNombre;

	public VistaBajaProducto() {
		initGUI();
	}
	
	void initGUI() {
		setTitle("Baja Producto");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel nombreLabel = new JLabel("Nombre: ");
		_tFNombre = new JTextField();
		_tFNombre.setPreferredSize(new Dimension(100, 20));
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(nombreLabel);
		nombrePanel.add(_tFNombre);
		mainPanel.add(nombrePanel);
		
		JPanel endPanel = new JPanel();
		JButton acceptBtn = new JButton("Eliminar");
		acceptBtn.addActionListener((e)-> bajaProducto());
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((e)-> dispose());
		endPanel.add(acceptBtn);
		endPanel.add(cancelBtn);
		mainPanel.add(endPanel);
		
	    setPreferredSize(new Dimension(400, 150));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void bajaProducto() {
		String nombre = _tFNombre.getText();

		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debes indicar un nombre", "Baja Producto", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dispose();
		Controlador.getInstance().accion(Evento.BAJA_PRODUCTO, nombre);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BAJA_PRODUCTO_SUCCESS:
			JOptionPane.showMessageDialog(this, "Producto con id " + datos + " dado de baja con éxito.", "Baja Producto", JOptionPane.INFORMATION_MESSAGE);
			break;
		case BAJA_PRODUCTO_ERROR:
			JOptionPane.showMessageDialog(this, "Error al dar de baja el producto: " + datos, "Baja Producto", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}
