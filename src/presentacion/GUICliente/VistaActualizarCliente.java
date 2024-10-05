package presentacion.GUICliente;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import negocio.Cliente.TCliente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaActualizarCliente extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	// Etiquetas
	private JLabel _lnombre;
	private JLabel _lapellidos;
	private JLabel _ldni;
	private JLabel _lcorreo;

	// Text Fields
	private JTextField _tfnombre;
	private JTextField _tfapellidos;
	private JTextField _tfdni;
	private JTextField _tfcorreo;

	// Botones
	private JButton _baceptar;
	private JButton _bcancelar;

	private final String RIGHTDATA = "OK";

	public VistaActualizarCliente() {
		this.initGUI();
	}

	private void initGUI() {
		this.setTitle("Actualizar Cliente");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		// Inicializamos etiquetas.
		this._lnombre = new JLabel("Nombre:");
		this._lapellidos = new JLabel("Apellidos:");
		this._ldni = new JLabel("DNI:");
		this._lcorreo = new JLabel("Correo:");

		// Inicializamos JTextFields.
		this._tfnombre = new JTextField();
		this._tfapellidos = new JTextField();
		this._tfdni = new JTextField();
		this._tfcorreo = new JTextField();

		this._tfnombre.setPreferredSize(new Dimension(100, 25));
		this._tfapellidos.setPreferredSize(new Dimension(100, 25));
		this._tfdni.setPreferredSize(new Dimension(100, 25));
		this._tfcorreo.setPreferredSize(new Dimension(100, 25));

		// Inicializamos botones
		this._baceptar = new JButton("ACEPTAR");
		this._bcancelar = new JButton("CANCELAR");
		this._baceptar.addActionListener((e) -> this.actualizarClienteListener());
		this._bcancelar.addActionListener((e) -> this.dispose());

		// Creamos paneles para colocar cada Label con su TextField.
		JPanel pnombre = new JPanel(new FlowLayout());
		JPanel papellidos = new JPanel(new FlowLayout());
		JPanel pdni = new JPanel(new FlowLayout());
		JPanel pcorreo = new JPanel(new FlowLayout());

		// Creamos panel para los botones
		JPanel buttonPanel = new JPanel();

		// Añdimos los campos a los paneles.
		pnombre.add(this._lnombre);
		pnombre.add(this._tfnombre);
		papellidos.add(this._lapellidos);
		papellidos.add(this._tfapellidos);
		pdni.add(this._ldni);
		pdni.add(this._tfdni);
		pcorreo.add(this._lcorreo);
		pcorreo.add(this._tfcorreo);

		buttonPanel.add(this._baceptar);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 5)));
		buttonPanel.add(this._bcancelar);
		buttonPanel.setBorder(BorderFactory.createMatteBorder(PROPERTIES, 0, 0, 0, getForeground()));

		// Añadimos paneles al panel principal.
		mainPanel.add(pnombre);
		mainPanel.add(papellidos);
		mainPanel.add(pdni);
		mainPanel.add(pcorreo);
		mainPanel.add(buttonPanel);

		setPreferredSize(new Dimension (400, 300));
		pack();
		setLocationRelativeTo(null); // Para que la ventana aparezca en el centro de la pantalla.
		setVisible(true);
	}

	private void actualizarClienteListener() {
		String nombre = this._tfnombre.getText();

		String apellidos = this._tfapellidos.getText();

		String dni = this._tfdni.getText();

		String correo = this._tfcorreo.getText();

		String checkvalid = this.validateData(nombre, apellidos, dni, correo);

		if (checkvalid != this.RIGHTDATA) {
			JOptionPane.showMessageDialog(this, checkvalid, "Actualizar Cliente", JOptionPane.ERROR_MESSAGE);
			return;
		}

		TCliente cliente = new TCliente(nombre, apellidos, dni, correo);

		this.dispose();
		Controlador.getInstance().accion(Evento.ACTUALIZAR_CLIENTE, cliente);
	}

	private String validateData(String nombre, String apellidos, String dni, String correo) {
		if (nombre == null || nombre.isBlank()) {
			return "Debes indicar el nombre del cliente.";
		} else if (apellidos == null || apellidos.isBlank()) {
			return "Debes indicar los apellidos del cliente.";
		} else if (dni == null || dni.isBlank()) {
			return "Debes indicar el DNI del cliente.";
		} else if (correo == null || correo.isBlank()) {
			return "Debes indicar el correo del cliente";
		}
		return this.RIGHTDATA;
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ACTUALIZAR_CLIENTE_SUCCESS:
			JOptionPane.showMessageDialog(this, "Cliente con id \"" + datos + "\" actualizado con éxito.", "Actualizar Producto", JOptionPane.INFORMATION_MESSAGE);
			break;
		case ACTUALIZAR_CLIENTE_ERROR:
			JOptionPane.showMessageDialog(this, "Error al actualizar cliente: " + datos, "Actualizar Producto", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}
