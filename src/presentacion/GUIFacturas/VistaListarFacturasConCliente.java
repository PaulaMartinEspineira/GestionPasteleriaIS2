package presentacion.GUIFacturas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import negocio.Facturas.TFactura;
import negocio.Facturas.TFacturasCliente;
import negocio.Cliente.TCliente;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaListarFacturasConCliente extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private ModeloTablaFacturas _modeloTabla;
	private JTextField _tFIdCliente;
	private JPanel _pedirIdClientePanel;
	private JPanel _infoPanel;
	private JPanel _mainPanel;
	private JPanel _tablePanel;

	public VistaListarFacturasConCliente() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Listar Facturas Con Cliente");

		// Creamos el panel
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		_pedirIdClientePanel = new JPanel();
		_pedirIdClientePanel.setLayout(new BoxLayout(_pedirIdClientePanel, BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		_mainPanel.add(_pedirIdClientePanel);

		// Id cliente
		_tFIdCliente = new JTextField();
		_tFIdCliente.setPreferredSize(new Dimension(100, 20));
		JPanel idFacturaPanel = new JPanel();
		idFacturaPanel.add(new JLabel("Id cliente: "));
		idFacturaPanel.add(_tFIdCliente);
		_pedirIdClientePanel.add(idFacturaPanel);

		// creamos el modelo de la tabla
		this._modeloTabla = new ModeloTablaFacturas();
		this._tablePanel = new JPanel();

		// Buscar y cancelar
		JPanel btnPanel = new JPanel();
		JButton acceptBtn = new JButton("Buscar");
		acceptBtn.addActionListener((e) -> {
			Listar();
			dispose();
		});

		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((e) -> dispose());

		btnPanel.add(acceptBtn);
		btnPanel.add(cancelBtn);
		_pedirIdClientePanel.add(btnPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void Listar() {
		int id_cliente;
		try {
			id_cliente = Integer.parseInt(this._tFIdCliente.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Debes indicar un id de cliente valido", "Listar Facturas Con Cliente",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Controlador.getInstance().accion(Evento.LISTAR_FACTURAS_CON_CLIENTE, id_cliente);
	}

	private void initInfoGUI(Object datos) {
		_infoPanel = new JPanel();
		JPanel _infoClientePanel = new JPanel();
		JPanel _clientePanel = new JPanel();
		_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));
		_mainPanel.add(_infoPanel);
		
		// Mostramos la informacion del cliente
		TCliente cliente = (TCliente) datos;
		JLabel idLabel = new JLabel("Id:" + cliente.getId());
		JLabel nombreLabel = new JLabel("Nombre: " + cliente.getNombre());
		JLabel apellidosLabel = new JLabel("Apellidos: " + cliente.getApellidos());
		JLabel DNILabel = new JLabel("DNI: " + cliente.getDNI());
		JLabel correoLabel = new JLabel("correo: " + cliente.getCorreo());
		JLabel activoLabel = new JLabel("activo: " + cliente.getActivo());
		_clientePanel.add(idLabel);
		_clientePanel.add(nombreLabel);
		_clientePanel.add(apellidosLabel);
		_clientePanel.add(DNILabel);
		_clientePanel.add(correoLabel);
		_clientePanel.add(activoLabel);

		_clientePanel.setLayout(new BoxLayout(_clientePanel, BoxLayout.Y_AXIS));
		_infoClientePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
				"Informacion del Cliente"));
		_infoClientePanel.add(_clientePanel);
		_infoPanel.add(_infoClientePanel);

		// Creamos la tabla de las facturas
		_tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
				"Facturas del Cliente"));
		_tablePanel.add(new JScrollPane(new JTable(this._modeloTabla), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		_infoPanel.add(_tablePanel);

		JPanel continuarPanel = new JPanel();
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener((e) -> dispose());
		continuarPanel.add(continuarBtn);
		_mainPanel.add(continuarPanel, BorderLayout.PAGE_END);

		pack();
	}

	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case LISTAR_FACTURAS_CON_CLIENTE_SUCCESS:
			_pedirIdClientePanel.setVisible(false);
			TFacturasCliente cliente_factura = (TFacturasCliente) datos;
			// mostramos el cliente
			TCliente cliente = cliente_factura.getCliente();
			initInfoGUI(cliente);
			// mostramos las facturas
			Collection<TFactura> facturas = cliente_factura.getFacturas();
			_modeloTabla.loadData(facturas);
			break;
		case LISTAR_FACTURAS_CON_CLIENTE_ERROR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos, "Listar Facturas con cliente",
					JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}
	}
}
