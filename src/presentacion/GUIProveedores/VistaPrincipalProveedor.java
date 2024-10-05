package presentacion.GUIProveedores;


import javax.swing.*;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;


public class VistaPrincipalProveedor extends JDialog implements IGUI{

	//vamos a tener un botón por cada vista
	//cuando los pulsemos se abrirá su JDialog correspondiente
	
	//los botones correspondiente como atributos (Quizá no es necesario que sean atributos)
	
	
	private static final long serialVersionUID = 1L;
	
	public VistaPrincipalProveedor() {
		initGUI();
	}
	
	private void initGUI(){
		setTitle("Subsistema Proveedor");
		
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel);
		
		JButton altaButton = new JButton("Alta Proveedor");
		altaButton.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ALTA_PROVEEDOR));
		buttonPanel.add(altaButton);
		
		JButton bajaButton = new JButton("Baja Proveedor");
		bajaButton.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BAJA_PROVEEDOR));
		buttonPanel.add(bajaButton);
		
		JButton actualizarButton = new JButton("Actualizar Proveedor");
		actualizarButton.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ACTUALIZAR_PROVEEDOR));
		buttonPanel.add(actualizarButton);
		
		JButton buscarButton = new JButton("Buscar Proveedor");
		buscarButton.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BUSCAR_PROVEEDOR));
		buttonPanel.add(buscarButton);
		
		JButton listarButton = new JButton("Listar Proveedores");
		listarButton.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_PROVEEDORES));
		buttonPanel.add(listarButton);
		
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
