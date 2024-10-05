package presentacion.GUICliente;

import java.awt.*;

import javax.swing.*;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaPrincipalCliente extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	
	// Botones
	private JButton _baltacliente;
	private JButton _bbajacliente;
	private JButton _bactualizarcliente;
	private JButton _bbuscarcliente;
	private JButton _blistarcliente;

	public VistaPrincipalCliente() {
		initGUI();
	}

	private void initGUI() {
		// Panel principal.
		JPanel mainPanel = new JPanel(new FlowLayout());
		this.setContentPane(mainPanel);
		
		// Panel para los botones.
		JPanel buttonPanel = new JPanel();

		// Incializamos botones y añadimos sus listeners.
		this._baltacliente = new JButton("Alta Cliente");
		this._bbajacliente = new JButton("Baja Cliente");
		this._bactualizarcliente = new JButton("Actualizar Cliente");
		this._bbuscarcliente = new JButton("Buscar Cliente");
		this._blistarcliente = new JButton("Listar Clientes");
		
		this._baltacliente.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ALTA_CLIENTE));
		this._bbajacliente.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BAJA_CLIENTE));
		this._bactualizarcliente.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ACTUALIZAR_CLIENTE));
		this._bbuscarcliente.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BUSCAR_CLIENTE));
		this._blistarcliente.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_CLIENTES));
		
		// Añadimos botones al panel.
		buttonPanel.add(this._baltacliente);
		buttonPanel.add(this._bbajacliente);
		buttonPanel.add(this._bactualizarcliente);
		buttonPanel.add(this._bbuscarcliente);
		buttonPanel.add(this._blistarcliente);
		
		mainPanel.add(buttonPanel);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null); // Para que la ventana aparezca en el centro de la pantalla.
		setVisible(true);
	}

	private void listenerAbrirVentana(Evento e) {
		Controlador.getInstance().accion(e, null);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
	}

}
