package presentacion.GUIFacturas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import negocio.Facturas.Carrito;
import java.awt.GridLayout;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;

public class VistaPrincipalFacturas extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	public VistaPrincipalFacturas() {
		initGUI();
	}

	private void initGUI() {
		setTitle("Subsistema Facturas");

		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);

		GridLayout grid = new GridLayout(4, 0);
		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel);
		buttonPanel.setLayout(grid);

		// Abrir Venta
		JButton abrirBtn = new JButton("Abrir venta");
		abrirBtn.addActionListener((e) -> {
			if (ControladorImp.carrito != null) {
				JOptionPane.showMessageDialog(null, "Ya hay una venta en curso, por favor cierre la venta primero",
						"Abrir Venta", 0);
			} else {
				ControladorImp.carrito = new Carrito();
				JOptionPane.showMessageDialog(null, "Carrito creado con exito", "Abrir Venta", 1);
			}
		});
		buttonPanel.add(abrirBtn);

		// Anadir Producto
		JButton annadirBtn = new JButton("Anadir producto");
		annadirBtn.addActionListener((e) -> {
			if (ControladorImp.carrito == null) {
				JOptionPane.showMessageDialog(null, "Por favor abra una venta primero", "Anadir producto", 0);
			} else {
				listenerAbrirVentana(Evento.ANADIR_PRODUCTO_CARGA);
			}
		});
		buttonPanel.add(annadirBtn);

		// Eliminar Producto
		JButton eliminarBtn = new JButton("Eliminar producto");
		eliminarBtn.addActionListener((e) -> {
			if (ControladorImp.carrito == null) {
				JOptionPane.showMessageDialog(null, "Por favor abra una venta primero", "Eliminar producto", 0);
			} else {
				listenerAbrirVentana(Evento.VISTA_ELIMINAR_PRODUCTO);
			}
		});
		buttonPanel.add(eliminarBtn);

		// Cerrar Venta
		JButton cerrarBtn = new JButton("Cerrar venta");
		cerrarBtn.addActionListener((e) -> {
			if (ControladorImp.carrito == null) {
				JOptionPane.showMessageDialog(null, "por favor abra una venta primero", "Cerrar Venta", 0);
			} else {
				listenerAbrirVentana(Evento.VISTA_CERRAR_VENTA);
			}
		});
		buttonPanel.add(cerrarBtn);

		// Ver Carrito
		JButton verCarritoBtn = new JButton("Ver carrito");
		verCarritoBtn.addActionListener((e) -> {
			if (ControladorImp.carrito == null) {
				JOptionPane.showMessageDialog(null, "Por favor abra una venta primero", "Ver carrito", 0);
			} else {
				listenerAbrirVentana(Evento.VISTA_VER_CARRITO);
			}
		});
		buttonPanel.add(verCarritoBtn);

		// Vaciar Carrito
		JButton carritoBtn = new JButton("Vaciar carrito");
		carritoBtn.addActionListener((e) -> {
			ControladorImp.carrito = new Carrito();
			JOptionPane.showMessageDialog(null, "Carrito vaciado con exito", "Vaciar carrito", 1);
		});
		buttonPanel.add(carritoBtn);

		// Listar factura
		JButton listarBtn = new JButton("Listar Facturas");
		listarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_FACTURAS));
		buttonPanel.add(listarBtn);

		// Listar factura POR cliente
		JButton listarPorClienteBtn = new JButton("Listar Facturas Por Cliente");
		listarPorClienteBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_FACTURAS_POR_CLIENTE));
		buttonPanel.add(listarPorClienteBtn);

		// Listar factura CON cliente
		JButton listarConClienteBtn = new JButton("Listar Facturas Con Cliente");
		listarConClienteBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_FACTURAS_CON_CLIENTE));
		buttonPanel.add(listarConClienteBtn);

		// Buscar factura
		JButton actualizarBtn = new JButton("Buscar factura");
		actualizarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BUSCAR_FACTURA));
		buttonPanel.add(actualizarBtn);

		// Modificar factura
		JButton bajaBtn = new JButton("Modificar factura");
		bajaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_MODIFICAR_FACTURA));
		buttonPanel.add(bajaBtn);

		// Devolucion factura
		JButton devolucionFacturaBtn = new JButton("Devolucion Factura");
		devolucionFacturaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_DEVOLUCION_FACTURA));
		buttonPanel.add(devolucionFacturaBtn);

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void listenerAbrirVentana(Evento e) {
		Controlador.getInstance().accion(e, null);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
	}

}
