package presentacion.GUICliente;

import javax.swing.*;
import java.awt.*;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBajaCliente extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;

	// Etiquetas
	private JLabel _ldni;
	
	// Text Fields
	private JTextField _tfdni;
	
	// Botones
	private JButton _baceptar;
	private JButton _bcancelar;
	
	public VistaBajaCliente() {
		this.initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Baja Cliente");
		
		// Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		
		// Creamos y añadimos text field y etiqueta.
		this._ldni = new JLabel("DNI:");
		this._tfdni = new JTextField();
		this._tfdni.setPreferredSize(new Dimension(100, 25));
		
		JPanel pdni = new JPanel(new FlowLayout());
		pdni.add(this._ldni);
		pdni.add(this._tfdni);
		
		// Creamos y añadimos botones.
		this._baceptar = new JButton("ACEPTAR");
		this._bcancelar = new JButton("CANCELAR");
		this._baceptar.addActionListener((e) -> this.bajaClienteListener());
		this._bcancelar.addActionListener((e) -> this.dispose());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this._baceptar);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 5)));
		buttonPanel.add(this._bcancelar);
		buttonPanel.setBorder(BorderFactory.createMatteBorder(PROPERTIES, 0, 0, 0, getForeground()));
		
		// Añadimos paneles al panel principal.
		mainPanel.add(pdni);
		mainPanel.add(buttonPanel);
		
		
		setPreferredSize(new Dimension (400, 150));
		pack();
		setLocationRelativeTo(null); // Para que la ventana aparezca en el centro de la pantalla.
		setVisible(true);
	}

	private void bajaClienteListener() {
		String dni = this._tfdni.getText();
		
		if (dni == null || dni.isBlank()) {
			JOptionPane.showMessageDialog(this, "Debes indicar un DNI.", "Baja Cliente", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		dispose();
		Controlador.getInstance().accion(Evento.BAJA_CLIENTE, dni);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BAJA_CLIENTE_SUCCESS:
			JOptionPane.showMessageDialog(this, "Cliente con id \"" + datos + "\" dado de baja con éxito.", "Baja Cliente", JOptionPane.INFORMATION_MESSAGE);
			break;
		case BAJA_CLIENTE_ERROR:
			JOptionPane.showMessageDialog(this, "Error al dar de baja cliente: " + datos, "Baja Cliente", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
	
}
