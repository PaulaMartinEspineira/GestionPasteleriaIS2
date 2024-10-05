package presentacion.GUIMarca;

import java.awt.Dimension;

import javax.swing.Box;
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

public class VistaBuscarMarca extends JDialog implements IGUI {

	private static final long serialVersionUID = 1L;
	
	JTextField nombreText;
	JButton okButton;
	JButton cancelButton;
	
	JPanel pedirNombrePanel;
	JPanel infoPanel;
	JPanel mainPanel;

	public VistaBuscarMarca() {
		initGUI();
	}
	
	
	void initGUI() {
		setTitle("Buscar Marca");

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		pedirNombrePanel = new JPanel();
		pedirNombrePanel.setLayout(new BoxLayout(pedirNombrePanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.add(pedirNombrePanel);
		
		
		
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreText = new JTextField();
		nombreText.setPreferredSize(new Dimension(100, 20));
		JPanel nombrePanel = new JPanel();
		nombrePanel.add(nombreLabel);
		nombrePanel.add(nombreText);
		pedirNombrePanel.add(nombrePanel);
		
		
		//Buscar/Cancel
		JPanel okCancelPanel = new JPanel();
		okButton = new JButton("Buscar");
		okButton.addActionListener((e)-> buscarMarca());
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> dispose());
		okCancelPanel.add(okButton);
		okCancelPanel.add(Box.createRigidArea(new Dimension(10, 5)));
		okCancelPanel.add(cancelButton);
		pedirNombrePanel.add(okCancelPanel);
	

		setPreferredSize(new Dimension (400, 150));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	void initInfoGUI(TMarca marca) {

		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		JLabel idLabel = new JLabel("Id: " + marca.getID());
		JLabel nombreLabel = new JLabel("Nombre: " + marca.getNombre());
		JLabel correoLabel = new JLabel("Correo: " + marca.getCorreo());
		String activo = marca.getActivo() ? "Si" : "No";
		JLabel activoLabel = new JLabel("Activo: " + activo);
		infoPanel.add(idLabel);
		infoPanel.add(nombreLabel);
		infoPanel.add(correoLabel);
		infoPanel.add(activoLabel);
		mainPanel.add(infoPanel);
		
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener((e) -> dispose());
		mainPanel.add(Box.createRigidArea(new Dimension(150, 20)));
		mainPanel.add(continuarBtn);
		
		setPreferredSize(new Dimension (400, 150));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void buscarMarca() {
		String nombre = (String) nombreText.getText();
		
		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Buscar Marca", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dispose();
		Controlador.getInstance().accion(Evento.BUSCAR_MARCA, nombre);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {	
		switch(e) {
		case BUSCAR_MARCA_SUCCESS:
			pedirNombrePanel.setVisible(false);
			initInfoGUI((TMarca) datos);
			break;
		case BUSCAR_MARCA_ERROR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos, "Buscar Marca", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}
