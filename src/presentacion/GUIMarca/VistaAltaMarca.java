package presentacion.GUIMarca;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Marca.TMarca;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaAltaMarca extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;
	
	JTextField nombreText;
	JTextField correoText;
	JButton okButton;
	JButton cancelButton;
	
	public VistaAltaMarca() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Alta Marca");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//Nombre
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreText = new JTextField();
		nombreText.setPreferredSize(new Dimension(100, 20));
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(nombreLabel);
		nombrePanel.add(nombreText);
		mainPanel.add(nombrePanel);
		
		//Correo
		JLabel correoLabel = new JLabel("Correo: ");
		correoText = new JTextField();
		correoText.setPreferredSize(new Dimension(100, 20));
		JPanel correoPanel = new JPanel();
		correoPanel.add(correoLabel);
		correoPanel.add(correoText);
		mainPanel.add(correoPanel);
		
		// Ok/Cancel
		JPanel okCancelPanel = new JPanel();
		okButton = new JButton("Aceptar");
		okButton.addActionListener((e)-> altaMarca());
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> dispose());
		okCancelPanel.add(okButton);
		okCancelPanel.add(cancelButton);
		mainPanel.add(okCancelPanel);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private void altaMarca() {
		TMarca marca;
		
		String nombre = nombreText.getText();
		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Alta Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String correo = correoText.getText();
		if (correo == null || correo.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un correo", "Alta Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (!correo.contains("@") || (!correo.contains(".es") && !correo.contains(".com"))) { //un correo es invalido si no tiene @ o no acaba en .com o .es
			JOptionPane.showMessageDialog(this, "Indique una dirección de correo válida", "Alta Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		marca = new TMarca(nombre, correo);
		Controlador.getInstance().accion(Evento.ALTA_MARCA, marca);
	}
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_MARCA_SUCCESS:
			JOptionPane.showMessageDialog(this, "Marca con id " + datos + " dado de alta con éxito", "Alta Marca", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case ALTA_MARCA_ERROR:
			JOptionPane.showMessageDialog(this, "Error al dar de alta la marca: " + datos, "Alta Marca", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
		
	}
}
