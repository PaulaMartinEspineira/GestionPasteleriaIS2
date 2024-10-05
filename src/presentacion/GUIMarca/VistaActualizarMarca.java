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

public class VistaActualizarMarca extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;

	JTextField nombreText;
	JTextField correoText;
	JButton okButton;
	JButton cancelButton;
	
	public VistaActualizarMarca() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Actualizar Marca");
		
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
		okButton.addActionListener((e)-> actualizarMarca());
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> dispose());
		okCancelPanel.add(okButton);
		okCancelPanel.add(cancelButton);
		mainPanel.add(okCancelPanel);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private void actualizarMarca() {
		TMarca marca;
		
		String nombre = nombreText.getText();
		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Actualizar Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String correo = correoText.getText();
		if (correo == null || correo.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un correo", "Actualizar Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (!correo.contains("@") || (!correo.contains(".es") && !correo.contains(".com"))) { //un correo es invalido si no tiene @ o no acaba en .com o .es
			JOptionPane.showMessageDialog(this, "Indique una dirección de correo válida", "Actualizar Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		marca = new TMarca(nombre, correo);
		Controlador.getInstance().accion(Evento.ACTUALIZAR_MARCA, marca);
	}
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ACTUALIZAR_MARCA_SUCCESS:
			JOptionPane.showMessageDialog(this, "Marca con id " + datos + " actualizado con éxito", "Actualizar Marca", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case ACTUALIZAR_MARCA_ERROR:
			JOptionPane.showMessageDialog(this, "Error al actualizar la marca: " + datos, "Actualizar Marca", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
		
	}
}
