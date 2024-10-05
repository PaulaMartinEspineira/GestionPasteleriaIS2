package presentacion.GUIProducto;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaPrincipalProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = -9126126716457163200L;

	public VistaPrincipalProducto() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Subsistema Producto");
		
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		
		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel);
		
		JButton altaBtn = new JButton("Alta Producto");
		altaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ALTA_PRODUCTO));
		buttonPanel.add(altaBtn);
		
		JButton bajaBtn = new JButton("Baja Producto");
		bajaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BAJA_PRODUCTO));
		buttonPanel.add(bajaBtn);
		
		JButton actualizarBtn = new JButton("Actualizar Producto");
		actualizarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ACTUALIZAR_PRODUCTO));
		buttonPanel.add(actualizarBtn);
		
		JButton buscarBtn = new JButton("Buscar Producto");
		buscarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BUSCAR_PRODUCTO));
		buttonPanel.add(buscarBtn);
		
		JButton listarBtn = new JButton("Listar Producto");
		listarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_PRODUCTO));
		buttonPanel.add(listarBtn);
		
		JButton listarPorMarcaBtn = new JButton("Listar Productos Por Marca");
		listarPorMarcaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_PRODUCTOS_POR_MARCA));
		buttonPanel.add(listarPorMarcaBtn);
		
		JButton listarConMarcaBtn = new JButton("Listar Productos Con Marca");
		listarConMarcaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_PRODUCTOS_CON_MARCA));
		buttonPanel.add(listarConMarcaBtn);
		
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
