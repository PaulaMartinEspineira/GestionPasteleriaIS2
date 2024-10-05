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

public class VistaAltaEmpleados extends JDialog implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ATRIBUTOS
	private JLabel etiquetaIdentificador;
	private JTextField textoIdentificador;

	private JLabel etiquetaNombre;
	private JTextField textoNombre;

	private JLabel etiquetaApellido;
	private JTextField textoApellido;

	private JLabel etiquetaTelefono;
	private JTextField textoTelefono;

	private JLabel etiquetaEmail;
	private JTextField textoEmail;

	private JLabel etiquetaDireccion;
	private JTextField textoDireccion;

	// botones
	private JButton buttonOkey;
	private JButton buttonCancel;

	public VistaAltaEmpleados() {
		initGUI();
	}

	private void initGUI() {

		setTitle("Alta empleados");

		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		;

		// IDENTIFICADOR
		JPanel panelIdentificador = new JPanel();
		this.etiquetaIdentificador = new JLabel("DNI: ");
		this.textoIdentificador = new JTextField(15);
		panelIdentificador.add(etiquetaIdentificador);
		panelIdentificador.add(textoIdentificador);

		// NOMBRE&APELLIDOS
		JPanel panelNombreApellido = new JPanel();
		this.etiquetaNombre = new JLabel("Nombre:");
		this.textoNombre = new JTextField(15);
		panelNombreApellido.add(etiquetaNombre);
		panelNombreApellido.add(textoNombre);
		this.etiquetaApellido = new JLabel("Apellido");
		this.textoApellido = new JTextField(15);
		panelNombreApellido.add(etiquetaApellido);
		panelNombreApellido.add(textoApellido);

		// TELÉFONO
		JPanel panelTelefono = new JPanel();
		this.etiquetaTelefono = new JLabel("Teléfono:");
		this.textoTelefono = new JTextField(15);
		panelTelefono.add(etiquetaTelefono);
		panelTelefono.add(textoTelefono);

		// EMAIL
		JPanel panelEmail = new JPanel();
		this.etiquetaEmail = new JLabel("email:");
		this.textoEmail = new JTextField(15);
		panelTelefono.add(etiquetaEmail);
		panelTelefono.add(textoEmail);

		// DIRECCION
		JPanel panelDireccion = new JPanel();
		this.etiquetaDireccion = new JLabel("Direccion:");
		this.textoDireccion = new JTextField(15);
		panelTelefono.add(etiquetaDireccion);
		panelTelefono.add(textoDireccion);

		// AÑADIMOS TODOS LOS PANELES AL main_Panel
		mainPanel.add(panelIdentificador);
		mainPanel.add(panelNombreApellido);
		mainPanel.add(panelTelefono);
		mainPanel.add(panelEmail);
		mainPanel.add(panelDireccion);

		// CREAMOS LOS BOTONES DE OK Y CANCEL

		// BUTTON OKEY
		JPanel okCancelPanel = new JPanel();
		this.buttonOkey = new JButton("Aceptar");
		this.buttonOkey.addActionListener((e) -> {
			altaEmpleado();
		});

		okCancelPanel.add(buttonOkey);

		// BUTTON CANCEL
		this.buttonCancel = new JButton("Cancelar");
		this.buttonCancel.addActionListener((e) -> {
			cancelImp(); //a lo mejor retocar esto?
		});

		okCancelPanel.add(buttonCancel);

		// añadimos ambos botones (ok y cancel) al main_panel
		mainPanel.add(okCancelPanel);
		
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);

	}


	private void altaEmpleado() {
		TEmpleado empleado;

		String identificador = textoIdentificador.getText();
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

		String nombre = textoNombre.getText();
		if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Alta Empleado", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String apellido = textoApellido.getText();
		if (apellido == null || apellido.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un apellido", "Alta Empleado", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String email = textoEmail.getText();
		if (email == null || email.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un correo", "Alta Empleado", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!email.contains("@")
				|| (!email.contains(".es") && !email.contains(".com") && !email.contains(".net"))) {
			JOptionPane.showMessageDialog(this, "Indique una dirección de correo válida", "Alta Marca",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String direccion = textoDireccion.getText();
		if (direccion == null || direccion.equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar una direccion ", "Alta Empleado",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String numeroTelefono = textoTelefono.getText();
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

		// creamos el empleado con los datos que han rellenado
		empleado = new TEmpleado(nombre, apellido, identificador, email, direccion, numeroTelefono);
		Controlador.getInstance().accion(Evento.ALTA_EMPLEADO, empleado);

	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case ALTA_EMPLEADO_EXITO:
			JOptionPane.showMessageDialog(this, "Empleado con id " + datos + " dado de alta con éxito", "Alta Empleado",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case ALTA_EMPLEADO_ERROR:
			JOptionPane.showMessageDialog(this, "Error al dar de alta al empleado: " + datos, "Alta Empleado",
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
