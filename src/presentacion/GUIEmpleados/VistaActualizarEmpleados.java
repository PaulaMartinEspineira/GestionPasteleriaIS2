package presentacion.GUIEmpleados;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.Empleados.TEmpleado;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaActualizarEmpleados extends JDialog implements IGUI {
  
	private static final long serialVersionUID = 1L;
	JTextField numeroTelefonoText;
	JTextField emailText;
	JTextField direccionText;
	JTextField DNIText;
	JButton buttonOkey;
	JButton buttonCancel;

	public VistaActualizarEmpleados() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Actualizar Empleados");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		// de los empleados podemos actualizar:
		// numero de telefono, correo, direccion
		// lo demás suponemos que no cambia
         
		
		//DNI
		JLabel DNIEtiqueta= new JLabel("DNI: ");
		DNIText= new JTextField(15);
		JPanel DNIPanel= new JPanel();
		DNIPanel.add(DNIEtiqueta);
		DNIPanel.add(DNIText);
		mainPanel.add(DNIPanel);
		
		
		// numeroTelefono
		JLabel numeroTelefonoEtiqueta = new JLabel("Numero de Telefono: ");
		numeroTelefonoText = new JTextField(15);
		JPanel numTelefonoPanel = new JPanel();
		numTelefonoPanel.add(numeroTelefonoEtiqueta);
		numTelefonoPanel.add(numeroTelefonoText);
		mainPanel.add(numTelefonoPanel);

		// direccion
		JLabel direccionEtiqueta = new JLabel("Direccion: ");
		direccionText = new JTextField(15);
		JPanel direccionPanel = new JPanel();
		direccionPanel.add(direccionEtiqueta);
		direccionPanel.add(direccionText);
		mainPanel.add(direccionPanel);

		// email
		JLabel emailEtiqueta = new JLabel("Email: ");
		emailText = new JTextField(15);
		JPanel emailPanel = new JPanel();
		emailPanel.add(emailEtiqueta);
		emailPanel.add(emailText);
		mainPanel.add(emailPanel);

		// CREAMOS LOS BOTONES DE OK Y CANCEL

		// BUTTON OKEY
		JPanel okCancelPanel = new JPanel();
		this.buttonOkey = new JButton("Aceptar");
		this.buttonOkey.addActionListener((e) -> {
			actualizarEmpleado();
		});

		okCancelPanel.add(buttonOkey);

		// BUTTON CANCEL
		this.buttonCancel = new JButton("Cancelar");
		this.buttonCancel.addActionListener((e) -> {
			cancelImp(); // a lo mejor retocar esto?
		});

		okCancelPanel.add(buttonCancel);

		// añadimos ambos botones (ok y cancel) al main_panel
		mainPanel.add(okCancelPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void actualizarEmpleado() {
		TEmpleado empleado;
        
		String identificador = DNIText.getText();
		if (identificador == null || identificador.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un DNI", "Alta Empleado", JOptionPane.ERROR_MESSAGE);
			return;
		}

		else if (identificador.length() > 9) {
			// ||//!identificador.contains(letra))

			JOptionPane.showMessageDialog(this, "Debe indicar un DNI válido", "Alta Empleado",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String email = emailText.getText();
		if (email == null || email.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un correo", "Alta Empleado", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!email.contains("@")
				|| (!email.contains(".es") && !email.contains(".com") && !email.contains(".net"))) {
			JOptionPane.showMessageDialog(this, "Indique una dirección de correo válida", "Alta Empleado",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String direccion = direccionText.getText();
		if (direccion == null || direccion.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar una direccion ", "Alta Empleado",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String numeroTelefono = numeroTelefonoText.getText();
		if (numeroTelefono == null || numeroTelefono.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un numero de telefono ", "Alta Empleado",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (numeroTelefono.length() > 9) {
			// signfiicaria que el numero de telefono tiene mas digitos de los que puede
			// realmente
			JOptionPane.showMessageDialog(this, "Indique un numero de telefono válido", "Alta Empleado",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		empleado = new TEmpleado(identificador, email, direccion, numeroTelefono);
		Controlador.getInstance().accion(Evento.ACTUALIZAR_EMPLEADO, empleado);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case ACTUALIZAR_EMPLEADO_EXITO:
			JOptionPane.showMessageDialog(this, "Empleado con id " + datos + " actualizado con éxito.", "Actualizar Empleado",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case ACTUALIZAR_EMPLEADO_ERROR:
			JOptionPane.showMessageDialog(this, "Error al actualizar al empleado: " + datos, "Actualizar Empleado",
					JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
	}
	
	private void cancelImp() {
		SwingUtilities.invokeLater(() -> {
			setVisible(false); //libera todos los componentes
		});
	}

}
