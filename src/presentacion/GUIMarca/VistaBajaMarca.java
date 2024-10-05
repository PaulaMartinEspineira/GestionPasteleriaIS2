package presentacion.GUIMarca;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Marca.MarcaProveedorTOA;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBajaMarca extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;

	JTextField nombreText;
	JButton okButton;
	JButton cancelButton;
	
	public VistaBajaMarca() {
		initGUI();
	}
	
	void initGUI() {
		setTitle("Baja Marca");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel idLabel = new JLabel("Nombre: ");
		nombreText = new JTextField();
		nombreText.setPreferredSize(new Dimension(100, 20));
		JPanel idPanel = new JPanel();
		idPanel.add(idLabel);
		idPanel.add(nombreText);
		mainPanel.add(idPanel);
		
		// Ok/Cancel
		JPanel okCancelPanel = new JPanel();
		okButton = new JButton("Aceptar");
		okButton.addActionListener((e)-> bajaMarca());
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> dispose());
		okCancelPanel.add(okButton);
		okCancelPanel.add(cancelButton);
		mainPanel.add(okCancelPanel);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	private void bajaMarca() {
		String nombre = nombreText.getText();

		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Baja Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Damos de baja todas las lineas de MarcaProveedor
		String nombreProv = null;
		MarcaProveedorTOA marcaProvTOA = new MarcaProveedorTOA();
		marcaProvTOA.bajaMarcaProveedor(nombre, nombreProv); //no hace falta lanzar la excepción porque si no existe la marca ya salta en DAObajaMarca();
		
		
		Controlador.getInstance().accion(Evento.BAJA_MARCA, nombre);
	}
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BAJA_MARCA_SUCCESS:
			JOptionPane.showMessageDialog(this, "Marca con id " + datos + " dado de baja con éxito.", "Baja Marca", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case BAJA_MARCA_ERROR:
			JOptionPane.showMessageDialog(this, "Error al dar de baja la marca: " + datos, "Baja Marca", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
		
	}

}
