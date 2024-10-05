package presentacion.GUICliente;

import javax.swing.*;

import negocio.Cliente.TCliente;

import java.awt.*;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBuscarCliente extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	
	// Paneles
	private JPanel _mainPanel;
	private JPanel _pedirDNIPanel;
	private JPanel _infoPanel;
	
	// Etiquetas
	private JLabel _ldni;
		
	// Text Fields
	private JTextField _tfdni;
		
	// Botones
	private JButton _baceptar;
	private JButton _bcancelar;
	
	public VistaBuscarCliente() {
		this.initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Buscar Cliente");
		
		// Panel principal.
		this._mainPanel = new JPanel();
		this._mainPanel.setLayout(new BoxLayout(this._mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(this._mainPanel);
		
		// Panel para pedir la información.
		this._pedirDNIPanel = new JPanel();
		this._pedirDNIPanel.setLayout(new BoxLayout(this._pedirDNIPanel, BoxLayout.Y_AXIS));
		this._mainPanel.add(this._pedirDNIPanel);
		
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
		this._baceptar.addActionListener((e) -> this.buscarClienteListener());
		this._bcancelar.addActionListener((e) -> this.dispose());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this._baceptar);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 5)));
		buttonPanel.add(this._bcancelar);
		
		// Añadimos paneles al panel principal.
		this._pedirDNIPanel.add(pdni);
		this._pedirDNIPanel.add(buttonPanel);
		
		
		setPreferredSize(new Dimension (400, 150));
		pack();
		setLocationRelativeTo(null); // Para que la ventana aparezca en el centro de la pantalla.
		setVisible(true);
	}
	
	private void buscarClienteListener() {
		String dni = this._tfdni.getText();
		
		if (dni == null || dni.isBlank()) {
			JOptionPane.showMessageDialog(this, "Debes indicar un DNI.", "Buscar Cliente", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		dispose();
		Controlador.getInstance().accion(Evento.BUSCAR_CLIENTE, dni);
	}
	
	private void initInfoGUI(TCliente cliente) {
		// Creamos un panel para mostrar la información y lo añadimos al principal.
		this._infoPanel = new JPanel();
		this._infoPanel.setLayout(new BoxLayout(this._infoPanel, BoxLayout.Y_AXIS));
		this._mainPanel.setLayout(new BorderLayout());
		this._mainPanel.add(this._infoPanel, BorderLayout.CENTER);
		
		// Añadimos los datos del cliente al panel.
		JLabel lnombre = new JLabel("Nombre: " + cliente.getNombre());
		JLabel lapellidos = new JLabel("Apellidos: " + cliente.getApellidos());
		JLabel ldni = new JLabel("DNI: " + cliente.getDNI());
		JLabel lcorreo = new JLabel("Correo: " + cliente.getCorreo());
		JLabel lactivo = new JLabel("Activo: " + (cliente.getActivo() ? "sí" : "no"));
		JLabel lid = new JLabel("ID: " + cliente.getId());
		
		this._infoPanel.add(lnombre);
		this._infoPanel.add(lapellidos);
		this._infoPanel.add(ldni);
		this._infoPanel.add(lcorreo);
		this._infoPanel.add(lactivo);
		this._infoPanel.add(lid);
		
		// Botón para continuar.
		JButton bcontinuar = new JButton("Continuar");
		JPanel buttonPanel = new JPanel();
		bcontinuar.addActionListener((e) -> dispose());
		buttonPanel.add(bcontinuar);
		
		this._mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		pack();
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BUSCAR_CLIENTE_SUCCESS:
			this._pedirDNIPanel.setVisible(false);
			this.initInfoGUI((TCliente) datos);
			break;
		case BUSCAR_CLIENTE_ERROR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos, "Buscar Cliente", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
	
}
